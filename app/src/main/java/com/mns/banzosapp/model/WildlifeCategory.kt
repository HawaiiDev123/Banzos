package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class WildlifeCategory : CommonResponse() {

    @SerializedName("bc_id")
    var bc_id: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("list")
    var list: List<CityRegionInsideDetails> = ArrayList()

    @SerializedName("fish_type")
    var fish_type: List<FishTypeDetails>? = null

}