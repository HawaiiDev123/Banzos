package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

data class ActivityMainListResponse(
    @SerializedName("heading")
    val heading: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("slider_base_url")
    val slider_base_url: String,
    @SerializedName("sliders")
    val sliders: List<CommonSliderDetails>,
    @SerializedName("activity_type")
    val activity_type: List<ActivityMainCategory>,
) : CommonResponse()
