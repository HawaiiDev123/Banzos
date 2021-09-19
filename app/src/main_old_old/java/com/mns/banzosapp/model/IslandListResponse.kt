package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.ResponseData

class IslandListResponse : ResponseData() {

    @SerializedName("image_base_url")
    var image_base_url: String? = null

    @SerializedName("islands")
    var islands:List<IslandDetails> = ArrayList()
}