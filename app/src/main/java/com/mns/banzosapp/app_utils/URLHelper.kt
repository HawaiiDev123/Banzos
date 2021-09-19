package com.mns.banzosapp.app_utils

object URLHelper {

    private const val PROTOCOL = "https://"
    private const val HOST = "explored.gohie.com/"
    private const val HOST_BASE = ""

    private const val API = "api/"

    private const val HostMainAddress = PROTOCOL + HOST + HOST_BASE + API
//    private const val ImageMainAddress = PROTOCOL + HOST + HOST_BASE


    //Image URLs
    var ISLAND_IMAGE_URL = ""


    //List APIs
    val FETCH_ISLAND_LIST = HostMainAddress + "list_island"

    val FETCH_INTRODUCTION = HostMainAddress + "introduction_details"

    val FETCH_GENERAL_INFO = HostMainAddress + "general_information_details"

    val FETCH_UPCOMING_EVENT = HostMainAddress + "upcoming_events_list"


    //Add APIs


    //Update APIs


    //Delete APIs
}