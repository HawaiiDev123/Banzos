package com.mns.banzosapp.helper.http

import com.android.volley.VolleyError
import com.mns.banzosapp.helper.app.AppController
import com.mns.banzosapp.helper.deserializer.BaseDeserializerList
import com.mns.banzosapp.model.base.ResponseData

class FetchList<T : ResponseData?>(
    private val listCommunicatorInterface: ListCommunicatorInterface
) {
    fun fetchList(
        url: String,
        mClass: Class<T>,
        param: Map<String, String>,
        tag: String
    ) {
        val baseHttpRequest = BaseHttpRequest(
            url,
            ResponseData::class.java,
            param,
            object : CallBackInterface<ResponseData> {
                override fun passResult(responseData: ResponseData) {
                    if (responseData.status) {
                        listCommunicatorInterface.onSuccess(responseData.data as List<*>)
                    } else {
                        listCommunicatorInterface.onFailed(responseData.message!!)
                    }
                }
            },
            { error -> listCommunicatorInterface.onError(error) },
            BaseDeserializerList(ResponseData::class.java, mClass)
        )
        AppController.instance.addToRequestQueue(baseHttpRequest, tag)
    }

    interface ListCommunicatorInterface {
        fun onError(error: VolleyError)
        fun onSuccess(updatedList: List<*>)
        fun onFailed(message: String)
    }
}
