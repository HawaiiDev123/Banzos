package com.mns.banzosapp.activities.beach

import android.os.Bundle
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AdapterOtherThingsToDoBeach
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.model.BeachFullDetails
import kotlinx.android.synthetic.main.activity_other_things_to_do_beach.*

class OtherThingsToDoBeachActivity : AppBaseActivity() {
    private lateinit var beachFullDetails: BeachFullDetails
    private lateinit var adapterOtherThingsToDoBeach: AdapterOtherThingsToDoBeach
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_things_to_do_beach)
        if (intent.hasExtra(AppConstants.INTENT_BEACH_DETAILS))
            beachFullDetails =
                intent.getSerializableExtra(AppConstants.INTENT_BEACH_DETAILS) as BeachFullDetails
        updateToolbar(getString(R.string.title_other_things_to_do))
        initializeAllView()
    }

    override fun initializeAllView() {
        adapterOtherThingsToDoBeach =
            AdapterOtherThingsToDoBeach(beachFullDetails.other_thinking_to_do_nearby)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        recyclerViewOtherThings.adapter = adapterOtherThingsToDoBeach
    }

    override fun clickListeners() {
        TODO("Not yet implemented")
    }
}
