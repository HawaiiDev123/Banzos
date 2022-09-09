package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class BeachInsideDetails : CommonResponse() {
    @SerializedName("RecordID")
    public var RecordID: Int? = null

    @SerializedName("title")
    public var title: String? = null

    @SerializedName("sub_title")
    public var sub_title: String? = null

    @SerializedName("mile")
    public var mile: String? = null

    @SerializedName("price")
    public var price: String? = null

    @SerializedName("rating")
    public var rating: String? = null

    @SerializedName("rating_star")
    public var rating_star: String? = null

    @SerializedName("image")
    public var image: MutableList<CommonSliderDetails> = ArrayList()
}