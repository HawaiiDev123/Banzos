package com.mns.banzosapp.activities.activitiesAndTours

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.volley.VolleyError
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.cart.AddToCartActivity
import com.mns.banzosapp.activities.gift.GiveAsGiftActivity
import com.mns.banzosapp.adapters.AdapterOtherActivityList
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.ActivityDetails
import com.mns.banzosapp.model.ActivityDetailsResponse
import com.mns.banzosapp.model.CityRegionInsideDetails
import kotlinx.android.synthetic.main.activity_detail_activity.*

class ActivityDetailActivity : AppBaseActivity(), View.OnClickListener {
    private lateinit var activityDetails: ActivityDetails
    private lateinit var activityMainDetails: CityRegionInsideDetails
    private lateinit var otherActivityDetailsList: MutableList<ActivityDetails>
    private lateinit var adapterOtherActivityList: AdapterOtherActivityList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activity)
        if (intent.hasExtra(AppConstants.INTENT_ACTIVITY_DETAILS))
            activityDetails =
                intent.getSerializableExtra(AppConstants.INTENT_ACTIVITY_DETAILS) as ActivityDetails
        if (intent.hasExtra(AppConstants.INTENT_ACTIVITY_MAIN_DETAILS))
            activityMainDetails =
                intent.getSerializableExtra(AppConstants.INTENT_ACTIVITY_MAIN_DETAILS) as CityRegionInsideDetails
        else {
            showMessage("Something went Wrong.")
            finish()
        }
        initializeAllView()
    }

    override fun initializeAllView() {
        otherActivityDetailsList = ArrayList()
        adapterOtherActivityList = AdapterOtherActivityList(otherActivityDetailsList)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        rv_otherActivities.adapter = adapterOtherActivityList
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadAllActivityDetails()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadAllActivityDetails()
        clickListeners()
    }

    private fun processToLoadAllActivityDetails() {
        showProgressDialog()
        val param = getLoginParam()
        param["RecordID"] = activityMainDetails.RecordID.toString()
        param["island"] = "kona"//activityMainDetails.island.toString()
        param["location_id"] = activityDetails.chechtimes_id
        FetchItem(object : FetchItem.ListCommunicatorInterface<ActivityDetailsResponse> {
            override fun onError(error: VolleyError) {
                dismissProgressDialog()
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: ActivityDetailsResponse) {
                dismissProgressDialog()
                enableSlider(fetchedDetails.web_activity_base_url, fetchedDetails.image)
                textViewActivityMainName.text = fetchedDetails.name
                updateToolbar(fetchedDetails.name)
                textViewDescription.text = fetchedDetails.description
                otherActivityDetailsList.clear()
                otherActivityDetailsList.addAll(fetchedDetails.other_activity)
                adapterOtherActivityList.notifyDataSetChanged()
                processToSetTopDetails(fetchedDetails.list)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_ACTIVITY_DETAILS,
            ActivityDetailsResponse::class.java,
            param,
            localClassName
        )
    }

    private fun processToSetTopDetails(activityDetails: ActivityDetails) {
        textViewPrice.text =
            "Your Price: \$${activityDetails.discount_price} From USD \$${activityDetails.original_price}"
        textViewReviews.text = "${activityDetails.review} Reviews"
        textViewActivityName.text = activityDetails.nameofactivity
        textViewCDOriginal.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.certified_driver_original_price
        )
        textViewCDDiscount.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.certified_driver_discount_price
        )
        textViewCDYOriginal.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.certified_driver_youth_original_price
        )
        textViewCDYDiscount.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.certified_driver_youth_discount_price
        )
        textViewRDOriginal.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.refresher_driver_original_price
        )
        textViewRDDiscount.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.refresher_driver_discount_price
        )
        textViewFDOriginal.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.free_driver_original_price
        )
        textViewFDDiscount.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.free_driver_discount_price
        )
        textViewRAOriginal.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.ride_along_original_price
        )
        textViewRADiscount.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.ride_along_discount_price
        )
        textViewJOWOriginal.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.junior_open_water_original_price
        )
        textViewJOWDiscount.text = String.format(
            getString(R.string.dollar_some_money),
            activityDetails.junior_open_water_discount_price
        )
        processToSetScubaDivingDetails(activityDetails)
    }

    private fun processToSetScubaDivingDetails(activityDetails: ActivityDetails) {
        textViewTripInformation.text = activityDetails.trip_information
        textViewWhatHarbor.text = activityDetails.what_harbor
        textViewSlipNumber.text = activityDetails.slip_number
        textViewCheckInTime.text = activityDetails.check_in_time
        textViewTripDeparts.text = activityDetails.trip_depart
        textViewTripReturns.text = activityDetails.trip_return
        textViewTripDuration.text = activityDetails.trip_duration
        textViewCapacity.text = activityDetails.capacity
        textViewIncludeInTrip.text = activityDetails.include_trip
        textViewWhatCanYouRent.text = activityDetails.rent_in_trip
//        textViewWhatBring.text = activityDetails.trip_information
        textViewDiscount.text = activityDetails.discount_available
        textViewHandicap.text = activityDetails.handicap_accessible
        textViewReqRes.text = activityDetails.requirement_restriction
//        textViewSDReviews.text = activityDetails.trip_information
        processToSetHighlightAndPolicy(activityDetails)
    }

    private fun processToSetHighlightAndPolicy(activityDetails: ActivityDetails) {
        textViewHighlights.text = activityDetails.highlights
        textViewCancellationPolicy.text = activityDetails.cancellation_policy
        textViewRefundPolicy.text = activityDetails.refund_policy
//        textViewWeather.text = activityDetails.highlights
//        textViewWeatherFee.text = activityDetails.highlights
//        textViewCancellation.text = activityDetails.highlights
//        textViewCancellationFee.text = activityDetails.highlights
//        textViewCruiseShip.text = activityDetails.highlights
//        textViewCruiseShipFee.text = activityDetails.highlights
        processToSetRatingReviews(activityDetails)
    }

    private fun processToSetRatingReviews(activityDetails: ActivityDetails) {
        textViewExcellentPercentage.text = String.format(
            getString(R.string.some_value_percentage),
            activityDetails.excellent_review
        )
        textViewVeryGoodPercentage.text = String.format(
            getString(R.string.some_value_percentage),
            activityDetails.verygood_review
        )
        textViewAvergaePercentage.text = String.format(
            getString(R.string.some_value_percentage),
            activityDetails.average_review
        )
        textViewPoorPercentage.text = String.format(
            getString(R.string.some_value_percentage),
            activityDetails.poor_review
        )
        textViewTerriblePercentage.text = String.format(
            getString(R.string.some_value_percentage),
            activityDetails.teribble_review
        )
    }

    override fun clickListeners() {
        cv_scubaDiving.setOnClickListener(this)
        cv_highlights.setOnClickListener(this)
        cv_policyDetail.setOnClickListener(this)
        tv_giveAsGift.setOnClickListener(this)
        tv_addToCart.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_giveAsGift -> {
                val intent = Intent(this, GiveAsGiftActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_addToCart -> {
                val intent = Intent(this, AddToCartActivity::class.java)
                startActivity(intent)
            }
            R.id.cv_scubaDiving -> {
                if (ll_scubaDiving.visibility == View.VISIBLE) {
                    ll_scubaDiving.visibility = View.GONE
                    iv_moreScuba.visibility = View.VISIBLE
                    iv_lessScuba.visibility = View.GONE
                } else {
                    ll_scubaDiving.visibility = View.VISIBLE
                    iv_moreScuba.visibility = View.GONE
                    iv_lessScuba.visibility = View.VISIBLE
                }
            }
            R.id.cv_highlights -> {
                if (ll_highlights.visibility == View.VISIBLE) {
                    ll_highlights.visibility = View.GONE
                    iv_moreHighlights.visibility = View.VISIBLE
                    iv_lessHighlights.visibility = View.GONE
                } else {
                    ll_highlights.visibility = View.VISIBLE
                    iv_moreHighlights.visibility = View.GONE
                    iv_lessHighlights.visibility = View.VISIBLE
                }
            }
            R.id.cv_policyDetail -> {
                if (ll_policyDetail.visibility == View.VISIBLE) {
                    ll_policyDetail.visibility = View.GONE
                    iv_morePolicy.visibility = View.VISIBLE
                    iv_lessPolicy.visibility = View.GONE
                } else {
                    ll_policyDetail.visibility = View.VISIBLE
                    iv_morePolicy.visibility = View.GONE
                    iv_lessPolicy.visibility = View.VISIBLE
                }
            }
        }
    }
}
