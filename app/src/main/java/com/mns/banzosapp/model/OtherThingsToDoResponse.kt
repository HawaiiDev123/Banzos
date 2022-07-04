package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class OtherThingsToDoResponse : CommonResponse() {

    @SerializedName("introduction")
    var introduction: String? = null

    @SerializedName("slider_base_url")
    var slider_base_url: String? = null

    @SerializedName("sliders")
    var sliders: List<CommonSliderDetails> = ArrayList()

    @SerializedName("other_things_category_list")
    var other_things_category_list: List<OtherThingsToDoDetails> = ArrayList()

}