package com.mns.banzosapp.app_utils;

import android.os.Build

object AppConstants {

    //Request Code
    const val ACTIVITY_FOR_RESULT_CHOOSE_PDF = 1001


    //Intent Names
    const val INTENT_USER_DETAILS = "userDetails"


    //Date & Time Formats
    val time24WithoutAMPM = if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) "KK:mm" else "HH:mm"
    val time24 = if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) "KK:mm a" else "HH:mm a"
    const val time12 = "hh:mm a"
    const val time12WithoutAMPM = "hh:mm"
    const val ServiceFormat = "yyyy-MM-dd"
    const val YearAndMonth = "yyyy-MM"
    val ServiceFormatFULL =
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) "yyyy-MM-dd KK:mm:ss" else "yyyy-MM-dd HH:mm:ss"
    const val DisplayFormat = "dd/MM/yyyy"
    const val DisplayFormatWithMonthName = "dd MMM. yyyy"
    const val DisplayFormatWithMonthNameAndTime = "dd MMM. yyyy hh:mm a"
    const val DisplayFormatOnlyDateAndMonth = "dd MMM."
}
