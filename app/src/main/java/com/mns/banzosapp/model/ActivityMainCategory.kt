package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class ActivityMainCategory : CommonResponse() {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("list")
    var list: List<CityRegionInsideDetails> = ArrayList()

}