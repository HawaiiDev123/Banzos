package com.mns.banzosapp.app_utils;

import android.os.Build

/**
 * Created by HusJCha
 * https://rajinfotech53.com
 * **/

object AppConstants {

    //Request Code
    const val ACTIVITY_FOR_RESULT_CHOOSE_PDF = 1001


    //Intent Names
    const val INTENT_USER_DETAILS = "userDetails"
    const val INTENT_FROM_NAME = "fromName"
    const val INTENT_INTRODUCTION_DETAILS = "introductionDetails"
    const val INTENT_CITY_TOWN_DETAILS = "cityTownDetails"
    const val INTENT_ISLAND_NAME = "islandName"
    const val INTENT_BEACH_DETAILS = "beachDetails"
    const val INTENT_ADVENTURE_ITEM = "adventure"
    const val INTENT_WILDLIFE_DETAILS = "wildLifeDetails"
    const val INTENT_ACTIVITY_MAIN_DETAILS = "activityMainDetails"
    const val INTENT_ACTIVITY_DETAILS = "activityDetails"


    //Screen Names
    const val SCREEN_INTRODUCTION = "introductionScreen"
    const val SCREEN_GENERAL_INFORMATION = "generalInformationScreen"


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
    const val DisplayFormatOnlyDate = "dd"
    const val DisplayFormatOnlyMonth = "MMM"
}
