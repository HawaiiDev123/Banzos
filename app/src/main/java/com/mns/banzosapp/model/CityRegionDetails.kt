package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class CityRegionDetails : CommonResponse() {

    @SerializedName("at_id")
    var at_id: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("region_title")
    var region_title: String? = null

    @SerializedName("subtitle")
    var subtitle: String? = null

    @SerializedName("list")
    var list: List<CityRegionInsideDetails> = ArrayList()



}