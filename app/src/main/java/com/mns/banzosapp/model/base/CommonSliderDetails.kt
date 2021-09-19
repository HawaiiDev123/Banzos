package com.mns.banzosapp.model.base

import com.google.gson.annotations.SerializedName

class CommonSliderDetails : ResponseData() {

    @SerializedName("image_nm")
    var image_nm: String? = null

    @SerializedName("type")
    var type: String? = null
}
