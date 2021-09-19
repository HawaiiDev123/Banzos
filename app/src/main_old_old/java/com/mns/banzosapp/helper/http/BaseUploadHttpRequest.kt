package com.mns.banzosapp.helper.http

import android.content.Context
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.GsonBuilder
import com.mns.banzosapp.common_util.LogUtil
import com.mns.banzosapp.helper.app.AppController
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.Buffer
import java.io.File
import java.io.IOException
import java.net.URLConnection

class BaseUploadHttpRequest<T>(
    context: Context,
    url: String,
    clazz: Class<T>,
    params: Map<String, String>,
    files: Map<String, File>,
    listener: Response.Listener<T>,
    errorListener: Response.ErrorListener?,
    deserializer: Any
) : Request<T>(Method.POST, url, errorListener) {

    private val context: Context
    private val deserializer: Any
    private val clazz: Class<T>
    private val listener: Response.Listener<T>
    private val timeoutRetryPolicy = 15000
    private val mParams = HashMap<String, String>()
    private val mFiles = HashMap<String, File>()
    private var mHeader = HashMap<String, String>()
    private var requestBody: RequestBody? = null

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        mHeader["accept"] = "application/json"
        mHeader["deviceid"] = AppController.instance.getDeviceId()
        if (mParams["Authorization"] != null && mParams.containsKey("Authorization")) {
            mHeader["Authorization"] =
                mParams["Authorization"].toString()
            mParams.remove("Authorization")
        }
        LogUtil.debug("--- Request : header $mHeader")
        LogUtil.debug("--- Request : param $mParams")
        LogUtil.debug("--- Request : File param $mFiles")
        return mHeader
    }

    private fun buildMultipartEntity(): RequestBody? {
        if (requestBody == null) {
            val multipartBuilder: MultipartBody.Builder = MultipartBody.Builder()
            multipartBuilder.setType(MultipartBody.FORM)
            for (param in mParams) {
                multipartBuilder.addFormDataPart(param.key, param.value)
            }
            for (file in mFiles) {
                val contentType: String = URLConnection.guessContentTypeFromName(file.value.name)
                multipartBuilder.addFormDataPart(
                    file.key, file.value.name,
                    file.value.asRequestBody(contentType.toMediaTypeOrNull())
                )
            }
            requestBody = multipartBuilder.build()
        }
        return requestBody
    }

    @Throws(AuthFailureError::class)
    override fun getBody(): ByteArray {
        val buffer = Buffer()
        try {
            buildMultipartEntity()?.writeTo(buffer)
        } catch (e: IOException) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream")
        }
        return buffer.readByteArray()
    }

    override fun getBodyContentType(): String {
        return buildMultipartEntity()?.contentType().toString()
    }

    override fun deliverResponse(response: T) {
        listener.onResponse(response)
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<T> {
        return try {
            val jsonStr = String(response.data, charset = Charsets.UTF_8)
            LogUtil.debug("---Response $jsonStr")
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(clazz, deserializer)
            val gson = gsonBuilder.create()
            /*
                 Into the object
                 */Response.success(
                gson.fromJson(jsonStr, clazz),
                HttpHeaderParser.parseCacheHeaders(response)
            )
            //return Response.success(jsonStr, HttpHeaderParser.parseCacheHeaders(response));
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance()
                .recordException(Exception(e))
            Response.error(ParseError(e))
        }
    }

    init {
        retryPolicy = DefaultRetryPolicy(
            timeoutRetryPolicy,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        this.context = context
        this.clazz = clazz
        this.listener = listener
        this.deserializer = deserializer
        mParams.clear()
        mFiles.clear()
        mFiles.putAll(files)
        mParams.putAll(params)
        buildMultipartEntity()
        LogUtil.debug("--- Request : url $url")
    }
}