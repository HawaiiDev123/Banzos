package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse

class AdventureCategory : CommonResponse() {

    @SerializedName("ac_id")
    var ac_id: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("region_list")
    var region_list: List<CityRegionDetails> = ArrayList()
}