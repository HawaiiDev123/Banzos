package com.mns.banzosapp.model.base

import com.google.gson.annotations.SerializedName

open class CommonResponse : ResponseData() {

    @SerializedName("image_base_url")
    var image_base_url: String? = null

    @SerializedName("meta")
    var meta: MetaResponseDetails? = null
}