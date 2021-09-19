package com.mns.banzosapp.model.base

import com.google.gson.annotations.SerializedName

class MetaResponseDetails : ResponseData() {
    @SerializedName("current_page")
    var current_page: String? = null

    @SerializedName("totalpage")
    var totalpage: String? = null

    @SerializedName("image_base_url")
    var image_base_url: String? = null

}
