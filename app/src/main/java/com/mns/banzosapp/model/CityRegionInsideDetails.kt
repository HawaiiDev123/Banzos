package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class CityRegionInsideDetails : CommonResponse() {
    @SerializedName("RecordID")
    public var RecordID: Int? = null

    @SerializedName("main_title")
    public var main_title: String? = null

    @SerializedName("title")
    public var title: String? = null

    @SerializedName("island")
    public var island: String? = null

    @SerializedName("address")
    public var address: String? = null

    @SerializedName("phone_no")
    public var phone_no: String? = null

    @SerializedName("nearby_milemaker")
    public var nearby_milemaker: String? = null

    @SerializedName("description")
    public var description: String? = null

    @SerializedName("game_description")
    public var game_description: String? = null

    @SerializedName("image")
    public var image: MutableList<CommonSliderDetails> = ArrayList()
    override fun toString(): String {
        if (title != null) {
            return title as String
        } else if (main_title != null) {
            return title as String
        }
        return ""
    }
}