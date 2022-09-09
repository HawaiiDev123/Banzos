package com.mns.banzosapp.activities.wildlife

import android.os.Bundle
import android.view.View
import com.android.volley.VolleyError
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.CityRegionInsideDetails
import com.mns.banzosapp.model.WildlifeFullDetails
import kotlinx.android.synthetic.main.activity_wildlife_detail.*

class WildlifeDetailActivity : AppBaseActivity(), View.OnClickListener {
    private lateinit var wildlifeDetails: CityRegionInsideDetails
    private lateinit var wildlifeFullDetails: WildlifeFullDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wildlife_detail)
        if (intent.hasExtra(AppConstants.INTENT_WILDLIFE_DETAILS))
            wildlifeDetails =
                intent.getSerializableExtra(AppConstants.INTENT_WILDLIFE_DETAILS) as CityRegionInsideDetails
        updateToolbar(wildlifeDetails.title.toString())
        initializeAllView()
    }

    override fun initializeAllView() {
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadWildLifeDetails()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadWildLifeDetails()
    }

    private fun processToLoadWildLifeDetails() {
        showProgressDialog()
        val param = getLoginParam()
        param["RecordID"] = wildlifeDetails.RecordID.toString()
        param["categoryTitle"] = "Gamefishing"
        FetchItem(object : FetchItem.ListCommunicatorInterface<WildlifeFullDetails> {
            override fun onError(error: VolleyError) {
                dismissProgressDialog()
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: WildlifeFullDetails) {
                dismissProgressDialog()
                wildlifeFullDetails = fetchedDetails
                processToSetDataInViews(fetchedDetails)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_WILD_LIFE_DETAILS,
            WildlifeFullDetails::class.java,
            param,
            localClassName
        )
    }

    private fun processToSetDataInViews(wildlifeFullDetails: WildlifeFullDetails) {
        enableSlider(wildlifeFullDetails.image_base_url, wildlifeFullDetails.image)
        tv_placeName.text = wildlifeFullDetails.title
        textViewPlaceDescription.text = wildlifeFullDetails.title
        textViewDescription.text = wildlifeFullDetails.description
        textViewGameRating.text = wildlifeFullDetails.game_rating
        textViewGameDescription.text = wildlifeFullDetails.game_description
        textViewAverageWeightLength.text = wildlifeFullDetails.average_weight_length
        textViewTackleBait.text = wildlifeFullDetails.tackle_and_baits
        carouselViewImages.setImageListener { position, imageView ->
            Glide.with(context)
                .load(wildlifeFullDetails.image_base_url + wildlifeFullDetails.image[position].image_nm)
                .placeholder(R.drawable.ic_sample5).into(imageView)
        }
        carouselViewImages.pageCount = wildlifeFullDetails.image.size

    }

    override fun clickListeners() {
    }

    override fun onClick(view: View?) {
    }
}
