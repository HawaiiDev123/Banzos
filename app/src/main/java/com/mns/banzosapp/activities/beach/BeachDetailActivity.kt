package com.mns.banzosapp.activities.beach

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.android.volley.VolleyError
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.BeachFullDetails
import com.mns.banzosapp.model.CityRegionInsideDetails
import kotlinx.android.synthetic.main.activity_beach_detail.*

class BeachDetailActivity : AppBaseActivity(), View.OnClickListener {
    private lateinit var beachDetails: CityRegionInsideDetails
    private lateinit var beachFullDetails: BeachFullDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beach_detail)
        beachDetails =
            intent.getSerializableExtra(AppConstants.INTENT_BEACH_DETAILS) as CityRegionInsideDetails
        updateToolbar(beachDetails.main_title.toString())
        initializeAllView()
    }

    override fun initializeAllView() {
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        processToLoadBeachDetails()
        clickListeners()
    }

    private fun processToLoadBeachDetails() {
        showProgressDialog()
        val param = getLoginParam()
        param["RecordID"] = beachDetails.RecordID.toString()
        FetchItem(object : FetchItem.ListCommunicatorInterface<BeachFullDetails> {
            override fun onError(error: VolleyError) {
                dismissProgressDialog()
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: BeachFullDetails) {
                dismissProgressDialog()
                beachFullDetails = fetchedDetails
                processToSetDataInViews(fetchedDetails)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_BEACH_DETAILS,
            BeachFullDetails::class.java,
            param,
            localClassName
        )
    }

    private fun processToSetDataInViews(beachFullDetails: BeachFullDetails) {
        enableSlider(beachFullDetails.image_base_url, beachFullDetails.image)
        textViewBeachTitle.text = beachFullDetails.title
        textViewBeachDescription.text = beachFullDetails.description
        textViewKidFriendly.text = beachFullDetails.kid_friendly
        textViewLifeGuard.text = beachFullDetails.lifeguards
        textViewMaximumDepth.text = beachFullDetails.maximum_depth
        textViewSnorkelingLevel.text = beachFullDetails.snorkeling_level
        textViewBestSnorkelTime.text = beachFullDetails.best_snorkel_time
        textViewBeachOpen.text = beachFullDetails.beach_open
        textViewBeachClosed.text = beachFullDetails.beach_closed
        textViewPhoneAddress.text = beachFullDetails.phone_no
        textViewExcellent.text = beachFullDetails.excellent
        textViewVeryGood.text = beachFullDetails.verygood
        textViewAverage.text = beachFullDetails.average
        textViewPoor.text = beachFullDetails.poor
        textViewTerrible.text = beachFullDetails.terrible
        progressLineWaterClarity.setmValueText("Water Clarity : ${beachFullDetails.water_clarity}/10")
        progressLineWaterClarity.setmPercentage(
            ((beachFullDetails.water_clarity?.toFloat() ?: 0).toInt() * 10)
        )
        progressLineWildLife.setmValueText("Wildlife Abundance : ${beachFullDetails.wildlife}/10")
        progressLineWildLife.setmPercentage(
            ((beachFullDetails.wildlife?.toFloat() ?: 0).toInt() * 10)
        )
        progressLineReef.setmValueText("Reef Clarity : ${beachFullDetails.reef}/10")
        progressLineReef.setmPercentage(
            ((beachFullDetails.reef?.toFloat() ?: 0).toInt() * 10)
        )
        progressLineEase.setmValueText("Ease of Beach Access : ${beachFullDetails.ease_to_beach}/10")
        progressLineEase.setmPercentage(
            ((beachFullDetails.ease_to_beach?.toFloat() ?: 0).toInt() * 10)
        )
        progressLineSany.setmValueText("Sany Beach For Entertaining : ${beachFullDetails.sany_beach}/10")
        progressLineSany.setmPercentage(
            ((beachFullDetails.sany_beach?.toFloat() ?: 0).toInt() * 10)
        )
        linearLayoutAminities.removeAllViews()
        for (amenity in beachFullDetails.amenities) {
            val imageViewAmenity =
                LayoutInflater.from(this)
                    .inflate(R.layout.amenity_activity_image, null) as ImageView
            val layoutParams = LinearLayout.LayoutParams(Utils.dpToPx(25), Utils.dpToPx(25))
            layoutParams.marginStart = Utils.dpToPx(5)
            layoutParams.marginEnd = Utils.dpToPx(5)
            imageViewAmenity.layoutParams = layoutParams
            Glide.with(context).load(amenity)
                .placeholder(R.drawable.ic_swimmer_black).into(imageViewAmenity)
            linearLayoutAminities.addView(imageViewAmenity)
        }
        linearLayoutActivities.removeAllViews()
        for (activity in beachFullDetails.activities) {
            val imageViewAmenity =
                LayoutInflater.from(this)
                    .inflate(R.layout.amenity_activity_image, null) as ImageView
            val layoutParams = LinearLayout.LayoutParams(Utils.dpToPx(25), Utils.dpToPx(25))
            layoutParams.marginStart = Utils.dpToPx(5)
            layoutParams.marginEnd = Utils.dpToPx(5)
            imageViewAmenity.layoutParams = layoutParams
            Glide.with(context).load(activity)
                .placeholder(R.drawable.ic_swimmer_black).into(imageViewAmenity)
            linearLayoutActivities.addView(imageViewAmenity)
        }
        Glide.with(context).load(beachFullDetails.map_image[0].image_nm)
            .placeholder(R.drawable.ic_sample1).fitCenter().into(imageViewMapImage)
    }

    override fun clickListeners() {
        linearLayoutNearbyPlaces.setOnClickListener(this)
        linearLayoutNearbyFood.setOnClickListener(this)
        linearLayoutOtherThingsToDo.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.linearLayoutNearbyPlaces -> {
                if (this::beachFullDetails.isInitialized) {
                    val intent = Intent(this, NearbyPlacesActivity::class.java)
                    intent.putExtra(AppConstants.INTENT_BEACH_DETAILS, beachFullDetails)
                    startActivity(intent)
                }
            }
            R.id.linearLayoutNearbyFood -> {
                if (this::beachFullDetails.isInitialized) {
                    val intent = Intent(this, NearbyFoodActivity::class.java)
                    intent.putExtra(AppConstants.INTENT_BEACH_DETAILS, beachFullDetails)
                    startActivity(intent)
                }
            }
            R.id.linearLayoutOtherThingsToDo -> {
                if (this::beachFullDetails.isInitialized) {
                    val intent = Intent(this, OtherThingsToDoBeachActivity::class.java)
                    intent.putExtra(AppConstants.INTENT_BEACH_DETAILS, beachFullDetails)
                    startActivity(intent)
                }
            }
        }
    }
}
