package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.ResponseData

class UserDetails : ResponseData() {

    @SerializedName("u_id")
    var u_id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("token")
    var token: String? = null

}