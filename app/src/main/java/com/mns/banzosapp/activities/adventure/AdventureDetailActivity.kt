package com.mns.banzosapp.activities.adventure

import android.os.Bundle
import com.android.volley.VolleyError
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.DayOfOpTimesAdapter
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.LogUtil
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.CityRegionInsideDetails
import com.mns.banzosapp.model.PointOfInterestDetails
import com.mns.banzosapp.model.TimeDetails
import kotlinx.android.synthetic.main.activity_point_of_interest_details.*

class AdventureDetailActivity : AppBaseActivity() {

    private lateinit var adventureDetails: CityRegionInsideDetails


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adventure_detail)

        adventureDetails =
            intent.getSerializableExtra(AppConstants.INTENT_ADVENTURE_ITEM) as CityRegionInsideDetails

        LogUtil.debug("RecordId===>"+adventureDetails.RecordID.toString());
        if (adventureDetails == null) {
            finish()
        }

        updateToolbar(adventureDetails.main_title.toString())
        initializeAllView()
    }

    override fun initializeAllView() {
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadAdventureDetailsData()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadAdventureDetailsData()
        setListsAndAdapters()
    }

    private fun processToLoadAdventureDetailsData() {

        showProgressDialog()
        val param = getLoginParam()
        param["RecordID"] = adventureDetails.RecordID.toString()

        FetchItem(object : FetchItem.ListCommunicatorInterface<PointOfInterestDetails> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: PointOfInterestDetails) {
                dismissProgressDialog()
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.image_base_url.toString()
                tv_placeName.text = Utils.fromHtml(fetchedDetails.title)
                enableSlider(fetchedDetails.image_base_url, fetchedDetails.image)
                setRegionData(fetchedDetails)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_ADVENTURE_DETAILS,
            PointOfInterestDetails::class.java,
            param,
            localClassName
        )
    }

    private fun setRegionData(regionTitle: PointOfInterestDetails) {
        tvPOIDRegionTitle.setText(regionTitle.region_title)
        tvPOIDLocationMap.setText(regionTitle.location_map)
        tvPOIDAddress.setText(regionTitle.address)
     //   tvPOIDCityStateZip.setText(regionTitle.city_state_zipcode)
        tvPOIDNearBy.setText(regionTitle.nearby_milemaker)
        tvPOIDWebsite.setText(regionTitle.website)
        tvPOIDPhoneNo.setText(regionTitle.phone_no)
        tvPOIDPricing.setText(regionTitle.pricing)
        tvPOIDDescription.setText(Utils.fromHtml(regionTitle.description))

        val timeDetailList: MutableList<TimeDetails> = regionTitle.time as MutableList<TimeDetails>
        val dayOfOpTimesAdapter = DayOfOpTimesAdapter(timeDetailList)
        rvPOIDTimeList.adapter = dayOfOpTimesAdapter
    }

    override fun setListsAndAdapters() {
        clickListeners()
    }

    override fun clickListeners() {
        
    }


}
