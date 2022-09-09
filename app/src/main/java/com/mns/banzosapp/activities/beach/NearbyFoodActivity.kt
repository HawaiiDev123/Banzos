package com.mns.banzosapp.activities.beach

import android.os.Bundle
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AdapterNearByFood
import com.mns.banzosapp.adapters.AdapterNearByPlaces
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.model.BeachFullDetails
import kotlinx.android.synthetic.main.activity_nearby_places.*

class NearbyFoodActivity : AppBaseActivity() {
    private lateinit var beachFullDetails: BeachFullDetails
    private lateinit var adapterNearByFood: AdapterNearByFood
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_food)
        if (intent.hasExtra(AppConstants.INTENT_BEACH_DETAILS))
            beachFullDetails =
                intent.getSerializableExtra(AppConstants.INTENT_BEACH_DETAILS) as BeachFullDetails
        updateToolbar(getString(R.string.title_nearby_food))
        initializeAllView()
    }

    override fun initializeAllView() {
        adapterNearByFood = AdapterNearByFood(beachFullDetails.nearby_food_location)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        recyclerViewNearbyPlaces.adapter = adapterNearByFood
    }

    override fun clickListeners() {
        TODO("Not yet implemented")
    }
}
