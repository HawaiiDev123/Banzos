package com.mns.banzosapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.IntroductionMainItemsAdapter
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.IntroductionDetails
import com.mns.banzosapp.model.IntroductionSubItemsDetails
import kotlinx.android.synthetic.main.activity_introduction.*
import kotlinx.android.synthetic.main.activity_introduction_details.*
import kotlinx.android.synthetic.main.row_home_island_list_item.*
import kotlinx.android.synthetic.main.toolbar_layout.*

//The same activity is used for General Information screen as well.
class IntroductionActivity : AppBaseActivity() {

    private lateinit var recyclerViewIntroductionMainItems: RecyclerView
    private lateinit var introductionSubItemsDetailsList: MutableList<IntroductionSubItemsDetails>
    private lateinit var introductionMainItemsAdapter: IntroductionMainItemsAdapter
    private var fromName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)
         fromName = intent.getStringExtra(AppConstants.INTENT_FROM_NAME)
        if (fromName == null) {
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
        recyclerViewIntroductionMainItems = findViewById(R.id.recyclerViewIntroductionMainItems)
        introductionSubItemsDetailsList = ArrayList()
        introductionMainItemsAdapter = IntroductionMainItemsAdapter(introductionSubItemsDetailsList)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        recyclerViewIntroductionMainItems.adapter = introductionMainItemsAdapter
        clickListeners()
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadIntroductionList()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadIntroductionList()
    }

    private fun processToLoadIntroductionList() {
        showProgressDialog()
        val param = getLoginParam()
          FetchItem(object : FetchItem.ListCommunicatorInterface<IntroductionDetails> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: IntroductionDetails) {
                dismissProgressDialog()
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                introductionSubItemsDetailsList.addAll(fetchedDetails.list)
                introductionMainItemsAdapter.notifyDataSetChanged()

                textViewIntroductionDescription.text = Utils.fromHtml(fetchedDetails.introduction)
                enableSlider(fetchedDetails.slider_base_url, fetchedDetails.sliders)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            if (fromName == AppConstants.SCREEN_INTRODUCTION) URLHelper.FETCH_INTRODUCTION else URLHelper.FETCH_GENERAL_INFO,
            IntroductionDetails::class.java,
            param,
            localClassName
        )
    }

    override fun clickListeners() {
        introductionMainItemsAdapter.setListener(object :
            IntroductionMainItemsAdapter.IntroductionListener {
            override fun onView(introductionSubItemsDetails: IntroductionSubItemsDetails) {
                val intent =
                    Intent(this@IntroductionActivity, IntroductionDetailsActivity::class.java)
                intent.putExtra(
                    AppConstants.INTENT_INTRODUCTION_DETAILS,
                    introductionSubItemsDetails
                )
                intent.putExtra(AppConstants.INTENT_FROM_NAME, fromName)
                startActivity(intent)
            }
        })
    }
}
