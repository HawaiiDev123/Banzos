package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class FishTypeDetails : CommonResponse() {

    @SerializedName("sub_title")
    var sub_title: String? = null

    @SerializedName("sub_list")
    var sub_list: List<CityRegionInsideDetails> = ArrayList()

}