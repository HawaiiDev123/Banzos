package com.mns.banzosapp.activities.activitiesAndTours

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.android.volley.VolleyError
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AdapterActivityMain
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.ActivityMainCategory
import com.mns.banzosapp.model.ActivityMainListResponse
import com.mns.banzosapp.model.CityRegionInsideDetails
import kotlinx.android.synthetic.main.activity_main_activity.*

class ActivityMainActivity : AppBaseActivity(), View.OnClickListener {
    private lateinit var activityMainCategoryList: MutableList<ActivityMainCategory>
    private lateinit var activityMainDetailList: MutableList<CityRegionInsideDetails>
    private lateinit var adapterActivityMain: AdapterActivityMain
    private var firstPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity)
        updateToolbar(getString(R.string.title_activities_tours))
        initializeAllView()
    }

    override fun initializeAllView() {
        activityMainCategoryList = ArrayList()
        activityMainDetailList = ArrayList()
        adapterActivityMain = AdapterActivityMain(activityMainDetailList)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        rv_beachList.adapter = adapterActivityMain
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadActivityList()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadActivityList()
        enableCommonSearch(object : SearchListener {
            override fun onSearch(text: String) {
                adapterActivityMain.filter.filter(text)
            }
        })
        clickListeners()
    }

    private fun processToLoadActivityList() {
        showProgressDialog()
        val param = getLoginParam()
        FetchItem(object : FetchItem.ListCommunicatorInterface<ActivityMainListResponse> {
            override fun onError(error: VolleyError) {
                dismissProgressDialog()
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: ActivityMainListResponse) {
                dismissProgressDialog()
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                activityMainCategoryList.clear()
                activityMainCategoryList.addAll(fetchedDetails.activity_type)
                setListAsPerView()
                tv_tutorialDesc.text = fetchedDetails.description
                enableSlider(fetchedDetails.slider_base_url, fetchedDetails.sliders)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_ACTIVITY_MAIN_LISTING,
            ActivityMainListResponse::class.java,
            param,
            localClassName
        )
    }

    private fun setListAsPerView() {
        tl_activities!!.removeAllTabs()
        for (activityMain in activityMainCategoryList) {
            tl_activities!!.addTab(tl_activities!!.newTab().setText(activityMain.title))
        }
        val root: View = tl_activities.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
    }

    override fun clickListeners() {
        tl_activities.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                firstPosition = tab!!.position
                activityMainDetailList.clear()
                activityMainDetailList.addAll(activityMainCategoryList[firstPosition].list)
                adapterActivityMain.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        adapterActivityMain.setListener(object : AdapterActivityMain.ActivityMainListener {
            override fun onView(activityMainDetails: CityRegionInsideDetails) {
                val intent = Intent(this@ActivityMainActivity, ActivityListActivity::class.java)
                intent.putExtra(AppConstants.INTENT_ACTIVITY_MAIN_DETAILS, activityMainDetails)
                startActivity(intent)
            }
        })
    }

    override fun onClick(view: View?) {
    }
}
