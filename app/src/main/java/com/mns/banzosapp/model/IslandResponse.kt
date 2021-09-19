package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse

class IslandResponse : CommonResponse() {

    @SerializedName("islands")
    var islands: List<IslandDetails> = ArrayList()
}