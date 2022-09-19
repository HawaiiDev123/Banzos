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

    val REGISTRATION = HostMainAddress + "registration"

    val FETCH_ISLAND_LIST = HostMainAddress + "list_island"

    val FETCH_INTRODUCTION = HostMainAddress + "introduction_list"

    val FETCH_GENERAL_INFO = HostMainAddress + "general_information_list"

    val FETCH_UPCOMING_EVENT = HostMainAddress + "upcoming_events_list"

    val FETCH_CITY_TOWN = HostMainAddress + "cities_towns_list"

    val FETCH_POINT_OF_INTEREST = HostMainAddress + "point_of_interest_lists"

    val FETCH_POINT_OF_INTEREST_DETAILS = HostMainAddress + "point_of_interest_details"

    val FETCH_OTHER_THINGS_TO_DO = HostMainAddress + "other_things_to_do_list"

    val FETCH_HEALTH_SAFETY_LIST = HostMainAddress + "health_and_safety_lists"

    val FETCH_BEST_BEACHES_LIST = HostMainAddress + "best_beaches_lists"

    val FETCH_BEACH_DETAILS = HostMainAddress + "best_beaches_details"

    val FETCH_WILD_LIFE = HostMainAddress + "wild_life_list"

    val FETCH_WILD_LIFE_DETAILS = HostMainAddress + "wild_life_detail"

    val FETCH_ACTIVITY_MAIN_LISTING = HostMainAddress + "activities_list"

    val FETCH_ACTIVITY_LISTING = HostMainAddress + "activities-middle-listing"

    val FETCH_ACTIVITY_DETAILS = HostMainAddress + "activity-details"

    val FETCH_ADVENTURE_LIST = HostMainAddress + "adventure_lists"

    val FETCH_ADVENTURE_DETAILS = HostMainAddress + "adventure_details"


    //Add APIs


    //Update APIs


    //Delete APIs
}