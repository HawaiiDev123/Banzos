package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class IntroductionResponse : CommonResponse() {

    @SerializedName("introduction")
    var introduction: String? = null

    @SerializedName("sliders")
    var sliders: List<CommonSliderDetails> = ArrayList()

    @SerializedName("list")
    var list: List<IntroductionDetails> = ArrayList()

}