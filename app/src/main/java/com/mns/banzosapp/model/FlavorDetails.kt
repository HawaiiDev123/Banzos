package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class FlavorDetails : CommonResponse() {
    @SerializedName("flavor")
    public var flavor: String? = null

    @SerializedName("texture")
    public var texture: String? = null

    @SerializedName("oil")
    public var oil: String? = null
}