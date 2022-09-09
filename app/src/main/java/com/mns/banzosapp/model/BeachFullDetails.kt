package com.mns.banzosapp.model

import com.google.gson.annotations.SerializedName
import com.mns.banzosapp.model.base.CommonResponse
import com.mns.banzosapp.model.base.CommonSliderDetails

class BeachFullDetails : CommonResponse() {
    @SerializedName("RecordID")
    public var RecordID: Int? = null

    @SerializedName("title")
    public var title: String? = null

    @SerializedName("city-state-zip")
    public var city_state_zip: String? = null

    @SerializedName("location_map")
    public var location_map: String? = null

    @SerializedName("amenities")
    public var amenities: List<String> = ArrayList()

    @SerializedName("description")
    public var description: String? = null

    @SerializedName("image")
    public var image: MutableList<CommonSliderDetails> = ArrayList()

    @SerializedName("activities")
    public var activities: List<String> = ArrayList()

    @SerializedName("lifeguards")
    public var lifeguards: String? = null

    @SerializedName("kid_friendly")
    public var kid_friendly: String? = null

    @SerializedName("maximum_depth")
    public var maximum_depth: String? = null

    @SerializedName("snorkeling_level")
    public var snorkeling_level: String? = null

    @SerializedName("best_snorkel_time")
    public var best_snorkel_time: String? = null

    @SerializedName("beach_open")
    public var beach_open: String? = null

    @SerializedName("beach_closed")
    public var beach_closed: String? = null

    @SerializedName("phone_no")
    public var phone_no: String? = null

    @SerializedName("excellent")
    public var excellent: String? = null

    @SerializedName("verygood")
    public var verygood: String? = null

    @SerializedName("average")
    public var average: String? = null

    @SerializedName("poor")
    public var poor: String? = null

    @SerializedName("terrible")
    public var terrible: String? = null

    @SerializedName("water_clarity")
    public var water_clarity: String? = null

    @SerializedName("wildlife")
    public var wildlife: String? = null

    @SerializedName("reef")
    public var reef: String? = null

    @SerializedName("ease_to_beach")
    public var ease_to_beach: String? = null

    @SerializedName("sany_beach")
    public var sany_beach: String? = null

    @SerializedName("direction_parking")
    public var direction_parking: String? = null

    @SerializedName("insider_tips")
    public var insider_tips: String? = null

    @SerializedName("nearby_places")
    public var nearby_places: MutableList<BeachInsideDetails> = ArrayList()

    @SerializedName("nearby_food_location")
    public var nearby_food_location: MutableList<BeachInsideDetails> = ArrayList()

    @SerializedName("other_thinking_to_do_nearby")
    public var other_thinking_to_do_nearby: MutableList<BeachInsideDetails> = ArrayList()

    @SerializedName("map_image")
    public var map_image: MutableList<CommonSliderDetails> = ArrayList()
}