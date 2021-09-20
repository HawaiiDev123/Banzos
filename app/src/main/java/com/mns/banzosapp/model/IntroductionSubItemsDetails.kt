package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails
import com.mns.banzosapp.model.base.ResponseData

class IntroductionSubItemsDetails : CommonResponse() {

    @SerializedName("RecordID")
    var RecordID: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("image")
    var image: MutableList<CommonSliderDetails> = ArrayList()

    @SerializedName("description")
    var description: String? = null


}