package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class HealthSafetyResponse : CommonResponse() {

    @SerializedName("introduction")
    var introduction: String? = null

    @SerializedName("slider_base_url")
    var slider_base_url: String? = null

    @SerializedName("safety_info")
    var safety_info: String? = null

    @SerializedName("sliders")
    var sliders: List<CommonSliderDetails> = ArrayList()

    @SerializedName("health_safety_category_list")
    var health_safety_category_list: List<HealthSafetyCategoryDetails> = ArrayList()

}