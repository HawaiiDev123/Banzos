package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class BestBeachesCategory : CommonResponse() {

    @SerializedName("bc_id")
    var bc_id: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("region_list")
    var region_list: List<CityRegionDetails> = ArrayList()

}