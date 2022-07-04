package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName

class TimeDetails : PointOfInterestDetails() {

    @SerializedName("day")
    var day: String? = null

    @SerializedName("day_start")
    var day_start: String? = null

    @SerializedName("day_end")
    var day_end: String? = null

    @SerializedName("is_closed")
    var is_closed: Int? = null
}