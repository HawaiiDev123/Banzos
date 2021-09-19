package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse

class UpcomingEventDetails : CommonResponse() {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("list")
    var list: List<UpcomingEventsSubDetails>? = ArrayList()


}