package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.ResponseData

class IslandDetails : ResponseData() {

    @SerializedName("mild_id")
    var mild_id: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("sub_title")
    var sub_title: String? = null

    @SerializedName("island_image")
    var island_image: String? = null
}
