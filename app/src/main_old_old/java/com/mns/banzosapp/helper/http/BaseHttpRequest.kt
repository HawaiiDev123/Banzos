package com.mns.banzosapp.helper.http

import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.GsonBuilder
import com.mns.banzosapp.common_util.LogUtil
import com.mns.banzosapp.helper.app.AppController
import java.nio.charset.StandardCharsets
import java.util.*

class BaseHttpRequest<T> internal constructor(
    url: String,
    clazz: Class<T>,
    params: Map<String, String>?,
    listener: CallBackInterface<T>?,
    errorListener: Response.ErrorListener?,
    deserializer: Any
) :
    Request<T>(Method.POST, url, errorListener) {
    private val deserializer: Any
    private val clazz: Class<T>
    private val listener: CallBackInterface<T>?
    private var mHeader = HashMap<String, String>()
    private val mParams = HashMap<String, String>()
    private val timeoutRetryPolicy = 60000
    private val authorizationParam = "Authorization"

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        mHeader = HashMap<String, String>()
        mHeader["accept"] = "application/json"
        mHeader["deviceid"] = AppController.instance.getDeviceId()
        if (mParams[authorizationParam] != null && mParams.containsKey(authorizationParam)) {
            mHeader[authorizationParam] =
                mParams[authorizationParam].toString()
            mParams.remove(authorizationParam)
        }
        LogUtil.debug("--- Request : param $mParams")
        LogUtil.debug("--- Request : header $mHeader")
        return mHeader
    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return mParams
    }

    override fun deliverResponse(response: T) {
        listener?.passResult(clazz.cast(response)!!)
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<T> {
        return try {
            /*
                 * Returns the data
                 */
            val jsonStr = String(response.data, StandardCharsets.UTF_8)
            LogUtil.debug("--- Response : $jsonStr")
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(clazz, deserializer)
            val gson = gsonBuilder.create()

            /*
                 * Into the object
                 */Response.success(
                gson.fromJson(jsonStr, clazz),
                HttpHeaderParser.parseCacheHeaders(response)
            )
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }

    init {
        retryPolicy = DefaultRetryPolicy(
            timeoutRetryPolicy,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        this.clazz = clazz
        this.listener = listener
        this.deserializer = deserializer
        mParams.clear()
        mParams.putAll(params!!)
        LogUtil.debug("--- Request : url $url")
    }
}