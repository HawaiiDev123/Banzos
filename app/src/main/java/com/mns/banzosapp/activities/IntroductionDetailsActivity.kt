package com.mns.banzosapp.activities

import android.os.Bundle
import com.mns.banzosapp.R
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.model.IntroductionSubItemsDetails
import kotlinx.android.synthetic.main.activity_introduction_details.*

class IntroductionDetailsActivity : AppBaseActivity() {

    private var introductionSubItemsDetails: IntroductionSubItemsDetails? = null
    private var fromName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_details)
        introductionSubItemsDetails =
            intent.getSerializableExtra(AppConstants.INTENT_INTRODUCTION_DETAILS) as IntroductionSubItemsDetails
        fromName = intent.getStringExtra(AppConstants.INTENT_FROM_NAME)
        if (introductionSubItemsDetails == null || fromName == null) {
            finish()
        }
        if (fromName?.equals(AppConstants.SCREEN_INTRODUCTION)!!) {
            updateToolbar(getString(R.string.title_introduction))
        } else {
            updateToolbar(getString(R.string.title_general_information))
        }
        initializeAllView()
    }

    override fun initializeAllView() {
        textViewIntroductionDetailsTitle.text = Utils.fromHtml(introductionSubItemsDetails!!.title)
        textViewIntroductionDetailsDescription.text =
            Utils.fromHtml(introductionSubItemsDetails!!.description)
        enableSlider(URLHelper.ISLAND_IMAGE_URL, introductionSubItemsDetails!!.image)
        clickListeners()
    }

    override fun setListsAndAdapters() {
    }

    override fun clickListeners() {
    }
}