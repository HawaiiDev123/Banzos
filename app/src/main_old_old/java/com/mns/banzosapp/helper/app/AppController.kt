package com.mns.banzosapp.helper.app

import android.app.Application
import android.provider.Settings
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.google.firebase.FirebaseApp

class AppController : Application() {
    private lateinit var mRequestQueue: RequestQueue


    val requestQueue: RequestQueue
        get() {
            if (!this::mRequestQueue.isInitialized) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }
            return mRequestQueue
        }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
        mRequestQueue = Volley.newRequestQueue(applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String) {
        // set the default tag if tag is empty
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        requestQueue.add(req)
    }

    fun cancelPendingRequests(tag: Any) {
        mRequestQueue.cancelAll(tag)
    }

    fun getDeviceId(): String {
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }

    companion object {
        val TAG = AppController

        @JvmStatic
        lateinit var instance: AppController
            private set
    }

    init {
        instance = this
    }
}
