package com.mns.banzosapp.app_utils

/**
 * Created by HusJCha
 * https://rajinfotech53.com
 * **/

object URLHelper {

    private const val PROTOCOL = "https://"
    private const val HOST = "explored.gohie.com/"
    private const val HOST_BASE = ""

    private const val API = "api/"

    private const val HostMainAddress = PROTOCOL + HOST + HOST_BASE + API

    
    //Image URLs
    var ISLAND_IMAGE_URL = ""


    //List APIs
    val LOGIN = HostMainAddress + "login"

    val FETCH_ISLAND_LIST = HostMainAddress + "list_island"

    val FETCH_INTRODUCTION = HostMainAddress + "introduction_list"

    val FETCH_GENERAL_INFO = HostMainAddress + "general_information_list"

    val FETCH_UPCOMING_EVENT = HostMainAddress + "upcoming_events_list"

    val FETCH_CITY_TOWN = HostMainAddress + "cities_towns_list"

    val FETCH_POINT_OF_INTEREST = HostMainAddress + "point_of_interest_lists"

    val FETCH_POINT_OF_INTEREST_DETAILS = HostMainAddress + "point_of_interest_details"

    val FETCH_OTHER_THINGS_TO_DO = HostMainAddress + "other_things_to_do_list"


    //Add APIs


    //Update APIs


    //Delete APIs
}