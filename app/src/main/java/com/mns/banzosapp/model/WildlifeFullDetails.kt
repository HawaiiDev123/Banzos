package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class WildlifeFullDetails : CommonResponse() {
    @SerializedName("island")
    public var island: String? = null

    @SerializedName("title")
    public var title: String? = null

    @SerializedName("description")
    public var description: String? = null

    @SerializedName("game_rating")
    public var game_rating: String? = null

    @SerializedName("game_description")
    public var game_description: String? = null

    @SerializedName("tackle_and_baits")
    public var tackle_and_baits: String? = null

    @SerializedName("image")
    public var image: MutableList<CommonSliderDetails> = ArrayList()

    @SerializedName("average_weight_length")
    public var average_weight_length: String? = null

    @SerializedName("nutritional")
    public var nutritional: NutritionDetails? = null

    @SerializedName("cooking")
    public var cooking: CookingDetails? = null

    @SerializedName("flavor_rating")
    public var flavor_rating: FlavorDetails? = null
}