package com.mns.banzosapp.activities.beach

import android.os.Bundle
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AdapterNearByPlaces
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.model.BeachFullDetails
import kotlinx.android.synthetic.main.activity_nearby_places.*

class NearbyPlacesActivity : AppBaseActivity() {
    private lateinit var beachFullDetails: BeachFullDetails
    private lateinit var adapterNearByPlaces: AdapterNearByPlaces
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_places)
        if (intent.hasExtra(AppConstants.INTENT_BEACH_DETAILS))
            beachFullDetails =
                intent.getSerializableExtra(AppConstants.INTENT_BEACH_DETAILS) as BeachFullDetails
        updateToolbar(getString(R.string.title_nearby_places))
        initializeAllView()
    }

    override fun initializeAllView() {
        adapterNearByPlaces = AdapterNearByPlaces(beachFullDetails.nearby_places)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        recyclerViewNearbyPlaces.adapter = adapterNearByPlaces
    }

    override fun clickListeners() {
        TODO("Not yet implemented")
    }
}
