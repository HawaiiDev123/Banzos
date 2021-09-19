package com.mns.banzosapp.model.base

import java.io.Serializable

open class ResponseData : Serializable {
    var status = false
    var message: String? = null
    var data: Any? = null

    fun release() {
        message = null
        data = null
        callGC()
    }

    private fun callGC() {}

    companion object {
        private const val serialVersionUID = 1L
    }
}