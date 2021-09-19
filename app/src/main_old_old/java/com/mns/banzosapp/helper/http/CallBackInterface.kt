package com.mns.banzosapp.helper.http

interface CallBackInterface<T> {
    fun passResult(responseData: T)
}