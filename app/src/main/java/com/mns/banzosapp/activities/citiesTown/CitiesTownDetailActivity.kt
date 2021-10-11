package com.mns.banzosapp.activities.citiesTown

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.model.CityRegionInsideDetails
import com.mns.banzosapp.model.IntroductionSubItemsDetails
import kotlinx.android.synthetic.main.activity_cities_town_detail.*
import kotlinx.android.synthetic.main.activity_introduction_details.*
import kotlinx.android.synthetic.main.activity_introduction_details.textViewIntroductionDetailsTitle
import kotlinx.android.synthetic.main.toolbar_layout.*

class CitiesTownDetailActivity : AppBaseActivity(){

    private var cityRegionInsideDetails: CityRegionInsideDetails? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_town_detail)
        cityRegionInsideDetails =
            intent.getSerializableExtra(AppConstants.INTENT_CITY_TOWN_DETAILS) as CityRegionInsideDetails

        if (cityRegionInsideDetails == null ) {
            finish()
        }
            updateToolbar(getString(R.string.title_city_details))
        initializeAllView()
    }

    override fun initializeAllView() {
        textViewCityTitle.text = Utils.fromHtml(cityRegionInsideDetails!!.main_title)
        textViewCityDescription.text =
            Utils.fromHtml(cityRegionInsideDetails!!.description)
        enableSlider(URLHelper.ISLAND_IMAGE_URL, cityRegionInsideDetails!!.image)
        clickListeners()
    }

    override fun setListsAndAdapters() {
    }

    override fun clickListeners() {
        setListsAndAdapters()
    }
}
