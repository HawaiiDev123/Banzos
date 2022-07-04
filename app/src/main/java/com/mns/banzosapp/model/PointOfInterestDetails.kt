package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

open class PointOfInterestDetails : CommonResponse() {

    @SerializedName("RecordID")
    var RecordID: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("image")
    var image: List<CommonSliderDetails> = ArrayList()

    @SerializedName("address")
    var address: String? = null

    @SerializedName("website")
    var website: String? = null

    @SerializedName("phone_no")
    var phone_no: String? = null

    @SerializedName("pricing")
    var pricing: String? = null

    @SerializedName("region_title")
    var region_title: String? = null

    @SerializedName("location_map")
    var location_map: String? = null

    @SerializedName("nearby_milemaker")
    var nearby_milemaker: String? = null

    @SerializedName("city-state-zipcode")
    var city_state_zipcode: String? = null

    @SerializedName("time")
    var time: List<TimeDetails> = ArrayList()
}
