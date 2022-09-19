package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

data class ActivityDetails(
    @SerializedName("web_activity_base_url")
    val web_activity_base_url: String,
    @SerializedName("checkintime")
    val checkintime: String,
    @SerializedName("starttime")
    val starttime: String,
    @SerializedName("endtime")
    val endtime: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("chechtimes_id")
    val chechtimes_id: String,
    @SerializedName("loc_act_id")
    val loc_act_id: String,
    @SerializedName("nameofactivity")
    val nameofactivity: String,
    @SerializedName("trip_information")
    val trip_information: String,
    @SerializedName("topratedactivity")
    val topratedactivity: String,
    @SerializedName("adultprice")
    val adultprice: String,
    @SerializedName("adultretaildiscount")
    val adultretaildiscount: String,
    @SerializedName("tripsummary")
    val tripsummary: String,
    @SerializedName("privatechartering")
    val privatechartering: String,
    @SerializedName("show_cased")
    val show_cased: String,
    @SerializedName("recommended")
    val recommended: String,
    @SerializedName("includetrip")
    val includetrip: String,
    @SerializedName("purchasetrip")
    val purchasetrip: String,
    @SerializedName("serviceprovidedby")
    val serviceprovidedby: String,
    @SerializedName("cancellationnofication")
    val cancellationnofication: String,
    @SerializedName("slider")
    val slider: List<CommonSliderDetails>,
    @SerializedName("image")
    val image: List<CommonSliderDetails>,
    @SerializedName("rating_star")
    val rating_star: String,
    @SerializedName("review")
    val review: String,
    @SerializedName("discount_price")
    val discount_price: String,
    @SerializedName("original_price")
    val original_price: String,
    @SerializedName("certified_driver_original_price")
    val certified_driver_original_price: String,
    @SerializedName("certified_driver_discount_price")
    val certified_driver_discount_price: String,
    @SerializedName("certified_driver_youth_original_price")
    val certified_driver_youth_original_price: String,
    @SerializedName("certified_driver_youth_discount_price")
    val certified_driver_youth_discount_price: String,
    @SerializedName("refresher_driver_original_price")
    val refresher_driver_original_price: String,
    @SerializedName("refresher_driver_discount_price")
    val refresher_driver_discount_price: String,
    @SerializedName("free_driver_original_price")
    val free_driver_original_price: String,
    @SerializedName("free_driver_discount_price")
    val free_driver_discount_price: String,
    @SerializedName("ride_along_original_price")
    val ride_along_original_price: String,
    @SerializedName("ride_along_discount_price")
    val ride_along_discount_price: String,
    @SerializedName("junior_open_water_original_price")
    val junior_open_water_original_price: String,
    @SerializedName("junior_open_water_discount_price")
    val junior_open_water_discount_price: String,
    @SerializedName("what_harbor")
    val what_harbor: String,
    @SerializedName("slip_number")
    val slip_number: String,
    @SerializedName("check_in_time")
    val check_in_time: String,
    @SerializedName("trip_depart")
    val trip_depart: String,
    @SerializedName("trip_return")
    val trip_return: String,
    @SerializedName("trip_duration")
    val trip_duration: String,
    @SerializedName("capacity")
    val capacity: String,
    @SerializedName("include_trip")
    val include_trip: String,
    @SerializedName("rent_in_trip")
    val rent_in_trip: String,
    @SerializedName("discount_available")
    val discount_available: String,
    @SerializedName("handicap_accessible")
    val handicap_accessible: String,
    @SerializedName("requirement_restriction")
    val requirement_restriction: String,
    @SerializedName("highlights")
    val highlights: String,
    @SerializedName("cancellation_policy")
    val cancellation_policy: String,
    @SerializedName("refund_policy")
    val refund_policy: String,
    @SerializedName("excellent_review")
    val excellent_review: String,
    @SerializedName("verygood_review")
    val verygood_review: String,
    @SerializedName("average_review")
    val average_review: String,
    @SerializedName("poor_review")
    val poor_review: String,
    @SerializedName("teribble_review")
    val teribble_review: String,
) : CommonResponse()
