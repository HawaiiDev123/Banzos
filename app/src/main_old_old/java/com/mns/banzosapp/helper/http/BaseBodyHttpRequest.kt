package com.mns.banzosapp.helper.http

import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.GsonBuilder
import com.mns.banzosapp.common_util.LogUtil
import com.mns.banzosapp.helper.app.AppController
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.StandardCharsets

class BaseBodyHttpRequest<T>(
    url: String?,
    clazz: Class<T>,
    params: JSONObject,
    listener: CallBackInterface<T>?,
    errorListener: Response.ErrorListener?,
    deserializer: Any
) :
    Request<T>(Method.POST, url, errorListener) {
    private val deserializer: Any
    private val clazz: Class<T>
    private val listener: CallBackInterface<T>?
    private val TIMEOUT_RETRY_POLICY = 60000
    private var mHeader = HashMap<String, String>()
    private var mParams = JSONObject()

    override fun getHeaders(): Map<String, String> {
        mHeader["accept"] = "application/json"
        mHeader["deviceid"] = AppController.instance.getDeviceId()
        try {
            mHeader["Authorization"] =
                mParams["Authorization"].toString()
            mParams.remove("Authorization")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        LogUtil.debug("--- Request : header $mHeader")
        return mHeader
    }

    override fun getBody(): ByteArray {
        return mParams.toString().toByteArray(StandardCharsets.UTF_8)
    }

    override fun deliverResponse(response: T) {
        listener?.passResult(clazz.cast(response)!!)
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<T> {
        return try {
            val jsonStr = String(response.data, StandardCharsets.UTF_8)
            LogUtil.debug("--- Response : $jsonStr")
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(clazz, deserializer)
            val gson = gsonBuilder.create()
            Response.success(
                gson.fromJson(jsonStr, clazz),
                HttpHeaderParser.parseCacheHeaders(response)
            )
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }

    init {
        retryPolicy = DefaultRetryPolicy(
            TIMEOUT_RETRY_POLICY,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        this.clazz = clazz
        this.listener = listener
        this.deserializer = deserializer
        mParams = params
        LogUtil.debug("--- Request : param $mParams")
    }
}
