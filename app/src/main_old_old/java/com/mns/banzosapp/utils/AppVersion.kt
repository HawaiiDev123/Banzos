package com.mns.banzosapp.utils

import android.os.Build

class AppVersion {

    companion object {

        /* fun isKitKatOrLater(): Boolean {
             return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
         }*/


        fun isMarshMallowOrLater(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        }

        /*fun isNougatOrLater(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
        }

        fun isOreoORLater(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        }*/
    }
}