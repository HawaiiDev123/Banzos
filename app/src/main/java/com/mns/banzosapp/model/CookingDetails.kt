package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse

class CookingDetails : CommonResponse() {
    @SerializedName("cooking_preparation")
    public var cooking_preparation: String? = null
}