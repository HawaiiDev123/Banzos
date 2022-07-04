package com.mns.banzosapp.activities.pointOfInterest

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.android.volley.VolleyError
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.citiesTown.CitiesTownDetailActivity
import com.mns.banzosapp.adapters.CityListItemAdapter
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.CityDetails
import com.mns.banzosapp.model.CityRegionDetails
import com.mns.banzosapp.model.CityRegionInsideDetails
import kotlinx.android.synthetic.main.activity_cities_town.*
import kotlinx.android.synthetic.main.activity_point_of_interest.*
import kotlinx.android.synthetic.main.activity_point_of_interest.tl_direction
import kotlinx.android.synthetic.main.toolbar_layout.*

class PointOfInterestActivity : AppBaseActivity() {

    private lateinit var cityRegionInsideDetails: MutableList<CityRegionInsideDetails>
    private lateinit var cityListItemAdapter: CityListItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_of_interest)

        initializeAllView()
    }

    override fun initializeAllView() {
        tv_title.text = "Point Of Interest"
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        cityRegionInsideDetails=ArrayList()
        cityListItemAdapter = CityListItemAdapter(cityRegionInsideDetails)
        recyclerViewPointOfInterestList.adapter = cityListItemAdapter
        clickListeners()

        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadPointOfInterestData()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadPointOfInterestData()
    }

    private fun processToLoadPointOfInterestData() {
        showProgressDialog()
        val param = getLoginParam()
        FetchItem(object : FetchItem.ListCommunicatorInterface<CityDetails> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: CityDetails) {
                dismissProgressDialog()
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                textViewPointOfInterestMainDesc.text = Utils.fromHtml(fetchedDetails.introduction)
                enableSlider(fetchedDetails.slider_base_url, fetchedDetails.sliders)
                cityRegionInsideDetails.addAll(fetchedDetails.region_title.get(0).list)
                setRegionData(fetchedDetails.region_title);
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_POINT_OF_INTEREST,
            CityDetails::class.java,
            param,
            localClassName
        )
    }

    override fun clickListeners() {
        cityListItemAdapter.setListener(object :CityListItemAdapter.CityItemListener{
            override fun onClick(cityRegionInsideDetails: CityRegionInsideDetails) {
                val intent =
                    Intent(this@PointOfInterestActivity, PointOfInterestDetailsActivity::class.java)
                intent.putExtra(
                    AppConstants.INTENT_CITY_TOWN_DETAILS,
                    cityRegionInsideDetails
                )
                startActivity(intent)
            }
        })
    }

    private fun setRegionData(regionList: List<CityRegionDetails>) {
        tl_direction!!.removeAllTabs()
        for (i in regionList) {
            tl_direction!!.addTab(tl_direction!!.newTab().setText(i.title + "\n" + i.subtitle))
        }
        cityListItemAdapter.notifyDataSetChanged()

        val root: View = tl_direction.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
        tl_direction!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                cityRegionInsideDetails.clear()
                cityRegionInsideDetails.addAll(regionList.get(tab.position).list)
                cityListItemAdapter.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

}
