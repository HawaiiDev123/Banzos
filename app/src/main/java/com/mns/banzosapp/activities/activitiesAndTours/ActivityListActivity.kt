package com.mns.banzosapp.activities.activitiesAndTours

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.android.volley.VolleyError
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.cart.AddToCartActivity
import com.mns.banzosapp.activities.gift.GiveAsGiftActivity
import com.mns.banzosapp.adapters.AdapterActivityList
import com.mns.banzosapp.adapters.base.EndlessScrollNestedScrollViewListener
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.ActivityDetails
import com.mns.banzosapp.model.ActivityListResponse
import com.mns.banzosapp.model.CityRegionInsideDetails
import kotlinx.android.synthetic.main.activity_list_activity.*

class ActivityListActivity : AppBaseActivity(), View.OnClickListener {
    private lateinit var activityMainDetails: CityRegionInsideDetails
    private lateinit var activityDetailsList: MutableList<ActivityDetails>
    private lateinit var adapterActivityList: AdapterActivityList
    private var firstPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_activity)
        if (intent.hasExtra(AppConstants.INTENT_ACTIVITY_MAIN_DETAILS))
            activityMainDetails =
                intent.getSerializableExtra(AppConstants.INTENT_ACTIVITY_MAIN_DETAILS) as CityRegionInsideDetails
        updateToolbar(activityMainDetails.title.toString())
        initializeAllView()
    }

    override fun initializeAllView() {
        activityDetailsList = ArrayList()
        adapterActivityList =
            AdapterActivityList(activityDetailsList, activityMainDetails.title.toString())
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        tl_activityList!!.removeAllTabs()
        for (activityTab in Utils.getActivityTabs()) {
            tl_activityList!!.addTab(tl_activityList!!.newTab().setText(activityTab.value))
        }
        val root: View = tl_activityList.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
        rv_activitiesList.adapter = adapterActivityList
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadActivitiesList(Utils.getActivityTabs()[firstPosition].key)
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadActivitiesList(Utils.getActivityTabs()[firstPosition].key)
        clickListeners()
    }

    private fun processToLoadActivitiesList(categoryTab: String) {
        if (adapterActivityList.getPageNumber() == "1")
            activityDetailsList.clear()
        showProgressDialog()
        val param = getLoginParam()
        param["RecordID"] = activityMainDetails.RecordID.toString()
        param["page"] = adapterActivityList.getPageNumber()
        param["island"] = "kona"//activityMainDetails.island.toString()
        param["category_tabs"] = categoryTab
        FetchItem(object : FetchItem.ListCommunicatorInterface<ActivityListResponse> {
            override fun onError(error: VolleyError) {
                dismissProgressDialog()
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: ActivityListResponse) {
                dismissProgressDialog()
                if (fetchedDetails.list.isEmpty()) {
                    adapterActivityList.setLoadingCompleted(true)
                }
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.slider_base_url.toString()
                enableSlider(fetchedDetails.meta?.slider_base_url, fetchedDetails.slider)
                activityDetailsList.clear()
                activityDetailsList.addAll(fetchedDetails.list)
                adapterActivityList.notifyDataSetChanged()
                textViewDescription.text = fetchedDetails.description
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_ACTIVITY_LISTING,
            ActivityListResponse::class.java,
            param,
            localClassName
        )
    }

    override fun clickListeners() {
        tl_activityList.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                firstPosition = tab?.position!!
                adapterActivityList.setPageNumber("1")
                if (isOnline(callBackForRetry))
                    processToLoadActivitiesList(Utils.getActivityTabs()[firstPosition].key)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        adapterActivityList.setEndlessNestedScrollViewListener(nestedScrollViewActivities,
            object : EndlessScrollNestedScrollViewListener(rv_activitiesList.layoutManager!!) {
                override fun onLoadMore() {
                    if (isOnline(callBackForRetry))
                        processToLoadActivitiesList(Utils.getActivityTabs()[firstPosition].key)
                }
            })
        adapterActivityList.setListener(object : AdapterActivityList.ActivityClickListener {
            override fun onView(activityDetails: ActivityDetails) {
                val intent = Intent(this@ActivityListActivity, ActivityDetailActivity::class.java)
                intent.putExtra(AppConstants.INTENT_ACTIVITY_DETAILS, activityDetails)
                intent.putExtra(AppConstants.INTENT_ACTIVITY_MAIN_DETAILS, activityMainDetails)
                startActivity(intent)
            }

            override fun onAddToCart(activityDetails: ActivityDetails) {
                val intent = Intent(this@ActivityListActivity, AddToCartActivity::class.java)
                intent.putExtra(AppConstants.INTENT_ACTIVITY_DETAILS, activityDetails)
                startActivity(intent)
            }

            override fun onGiveAsGift(activityDetails: ActivityDetails) {
                val intent = Intent(this@ActivityListActivity, GiveAsGiftActivity::class.java)
                intent.putExtra(AppConstants.INTENT_ACTIVITY_DETAILS, activityDetails)
                startActivity(intent)
            }
        })
    }

    override fun onClick(view: View?) {
    }
}

