package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse

class UpcomingEventsSubDetails : CommonResponse() {

    @SerializedName("RecordID")
    var RecordID: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("event_date")
    var event_date: String? = null

    @SerializedName("location")
    var location: String? = null

}