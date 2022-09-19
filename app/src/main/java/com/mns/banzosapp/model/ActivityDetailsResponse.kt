package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

data class ActivityDetailsResponse(
    @SerializedName("RecordID")
    val RecordID: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("web_activity_base_url")
    val web_activity_base_url: String,
    @SerializedName("slider")
    val slider: List<CommonSliderDetails>,
    @SerializedName("image")
    val image: List<CommonSliderDetails>,
    @SerializedName("other_activity")
    val other_activity: List<ActivityDetails>,
    @SerializedName("list")
    val list: ActivityDetails,
) : CommonResponse()
