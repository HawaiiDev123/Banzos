package com.mns.banzosapp.activities

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.model.IntroductionSubItemsDetails
import com.mns.banzosapp.utils.Global
import kotlinx.android.synthetic.main.activity_introduction_details.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class IntroductionDetailsActivity : AppBaseActivity(), View.OnClickListener {


    lateinit var introductionSubItemsDetails: IntroductionSubItemsDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction_details)

        if (intent.extras != null && intent.hasExtra(Global.INTRODETAILS)) {
            introductionSubItemsDetails =
                intent.getSerializableExtra(Global.INTRODETAILS) as IntroductionSubItemsDetails
        }
        initializeAllView()

    }

    override fun initializeAllView() {

        textViewIntroductionDetailsTitle.text = Html.fromHtml(introductionSubItemsDetails.title)
        textViewIntroductionDetailsDescription.text = Html.fromHtml(introductionSubItemsDetails.description)

        if(introductionSubItemsDetails.image?.size==0) {
            Glide.with(this@IntroductionDetailsActivity).load(R.drawable.home01)
                .placeholder(R.drawable.home01)
                .into(imageViewIntroductionSubItems)
        }
        else
        {
            Glide.with(this@IntroductionDetailsActivity).load(
                URLHelper.ISLAND_IMAGE_URL+introductionSubItemsDetails.image?.get(0)?.image_nm)
                .placeholder(R.drawable.home01)
                .into(imageViewIntroductionSubItems)
        }

        clickListeners()
    }

    override fun setListsAndAdapters() {
    }

    override fun clickListeners() {
        iv_back.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }

}