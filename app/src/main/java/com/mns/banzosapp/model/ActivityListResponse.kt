package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

data class ActivityListResponse(
    @SerializedName("RecordID")
    val RecordID: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("slider_base_url")
    val slider_base_url: String,
    @SerializedName("slider")
    val slider: List<CommonSliderDetails>,
    @SerializedName("image")
    val image: List<CommonSliderDetails>,
    @SerializedName("list")
    val list: List<ActivityDetails>,
) : CommonResponse()
