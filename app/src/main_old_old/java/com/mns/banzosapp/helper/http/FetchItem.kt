package com.mns.banzosapp.helper.http

import com.android.volley.VolleyError
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.mns.banzosapp.helper.app.AppController
import com.mns.banzosapp.helper.deserializer.BaseDeserializer
import com.mns.banzosapp.model.base.ResponseData

class FetchItem<T : ResponseData>(
    private val listCommunicatorInterface: ListCommunicatorInterface<T>
) {
    fun fetchItem(
        url: String,
        mClass: Class<T>,
        param: Map<String, String>,
        tag: String
    ) {
        val baseHttpRequest = BaseHttpRequest(url, mClass, param, object : CallBackInterface<T> {
            override fun passResult(responseData: T) {
                if (responseData.status) {
                    listCommunicatorInterface.onSuccess(responseData)
                } else {
                    listCommunicatorInterface.onFailed(responseData.message!!)
                }
            }
        }, { error ->
            listCommunicatorInterface.onError(error)
            FirebaseCrashlytics.getInstance()
                .recordException(Exception(error))
        }, BaseDeserializer(mClass))
        AppController.instance.addToRequestQueue(baseHttpRequest, tag)
    }

    interface ListCommunicatorInterface<T> {
        fun onError(error: VolleyError)
        fun onSuccess(fetchedDetails: T)
        fun onFailed(message: String)
    }
}
