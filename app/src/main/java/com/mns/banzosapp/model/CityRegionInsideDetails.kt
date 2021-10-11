package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class CityRegionInsideDetails : CommonResponse() {

    @SerializedName("RecordID")
    var RecordID: Int? = null

    @SerializedName("main_title")
    var main_title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("image")
    var image: MutableList<CommonSliderDetails> = ArrayList()

}