package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class NutritionDetails : CommonResponse() {
    @SerializedName("serving_size_of")
    public var serving_size_of: String? = null

    @SerializedName("calories")
    public var calories: String? = null

    @SerializedName("fat_calories")
    public var fat_calories: String? = null

    @SerializedName("total_fat")
    public var total_fat: String? = null

    @SerializedName("saturated_fat")
    public var saturated_fat: String? = null

    @SerializedName("cholesterol")
    public var cholesterol: String? = null

    @SerializedName("sodium")
    public var sodium: String? = null

    @SerializedName("potassium")
    public var potassium: String? = null

    @SerializedName("protein")
    public var protein: String? = null

    @SerializedName("omega_3")
    public var omega_3: String? = null
}