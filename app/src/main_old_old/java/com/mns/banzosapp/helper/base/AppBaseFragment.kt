package com.mns.banzosapp.helper.base

import android.view.View

abstract class AppBaseFragment : BaseFragment() {
    abstract fun initializeAllView(view: View)
    abstract fun setListsAndAdapters()
    abstract fun clickListeners()

    fun getParam(): HashMap<String, String> {
        return HashMap()
    }

    fun getLoginParam(): HashMap<String, String> {
        val param = HashMap<String, String>()
        param["customer_id"] = prefs.getUserId()!!
        param["Authorization"] = "Bearer " + prefs.getUserToken()!!
        return param
    }
}