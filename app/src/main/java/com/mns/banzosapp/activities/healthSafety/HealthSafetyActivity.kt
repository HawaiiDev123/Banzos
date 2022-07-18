package com.mns.banzosapp.activities.healthSafety

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import com.android.volley.VolleyError
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AdapterHealthSafetyList
import com.mns.banzosapp.adapters.base.EndlessScrollNestedScrollViewListener
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.CityRegionInsideDetails
import com.mns.banzosapp.model.HealthSafetyCategoryDetails
import com.mns.banzosapp.model.HealthSafetyResponse
import kotlinx.android.synthetic.main.activity_health_safety.*

class HealthSafetyActivity : AppBaseActivity(), View.OnClickListener {
    private lateinit var healthSafetyCategoryList: MutableList<HealthSafetyCategoryDetails>
    private lateinit var healthSafetyDetailsList: MutableList<CityRegionInsideDetails>
    private lateinit var adapterHealthSafetyList: AdapterHealthSafetyList
    private var firstPosition = 0
    private var secondPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_safety)
        updateToolbar(getString(R.string.title_health_safety))
        initializeAllView()
    }

    override fun initializeAllView() {
        healthSafetyCategoryList = ArrayList()
        healthSafetyDetailsList = ArrayList()
        adapterHealthSafetyList = AdapterHealthSafetyList(healthSafetyDetailsList)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        rv_healthAndSafety.adapter = adapterHealthSafetyList
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadOtherThingsToDo()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadOtherThingsToDo()
        clickListeners()
    }

    override fun clickListeners() {
        adapterHealthSafetyList.setListener(object :
            AdapterHealthSafetyList.HealthSafetyClickListener {
            override fun onShare(healthSafetyDetails: CityRegionInsideDetails) {
                openAddToTripPlannerDialog()
            }

            override fun onCall(healthSafetyDetails: CityRegionInsideDetails) {
                goToCallWithNumber(healthSafetyDetails.phone_no.toString())
            }
        })
        adapterHealthSafetyList.setEndlessNestedScrollViewListener(
            nestedScrollViewHealthSafety,
            object :
                EndlessScrollNestedScrollViewListener(rv_healthAndSafety.layoutManager!!) {
                override fun onLoadMore() {
                    if (isOnline())
                        processToLoadOtherThingsToDo()
                }
            })
        tl_healthAndSafety.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                firstPosition = tab!!.position
                tl_healthAndSafety_2.removeAllTabs()
                val otherThingRegionList = healthSafetyCategoryList[tab.position].region_list
                for (otherThingRegion in otherThingRegionList)
                    tl_healthAndSafety_2!!.addTab(
                        tl_healthAndSafety_2!!.newTab()
                            .setText(otherThingRegion.region_title + "\n" + otherThingRegion.subtitle)
                    )
                val root: View = tl_healthAndSafety_2.getChildAt(0)
                if (root is LinearLayout) {
                    root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
                    val drawable = GradientDrawable()
                    drawable.setColor(resources.getColor(R.color.transparent))
                    drawable.setSize(2, 1)
                    root.dividerPadding = 25
                    root.dividerDrawable = drawable
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        tl_healthAndSafety_2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                secondPosition = tab!!.position
                val category = healthSafetyCategoryList[firstPosition].title
                if (category == "Safety info") {
                    rv_healthAndSafety.visibility = View.GONE
                    ll_safetyInfo.visibility = View.VISIBLE
                } else {
                    rv_healthAndSafety.visibility = View.VISIBLE
                    ll_safetyInfo.visibility = View.GONE
                    healthSafetyDetailsList.clear()
                    healthSafetyDetailsList.addAll(healthSafetyCategoryList[firstPosition].region_list[secondPosition].list)
                    adapterHealthSafetyList.notifyDataSetChanged()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun goToCallWithNumber(phoneNo: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNo")
        startActivity(intent)
    }

    private fun openAddToTripPlannerDialog() {
        val addToTripPlannerDialog = Dialog(this)
        addToTripPlannerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addToTripPlannerDialog.setCancelable(true)
        addToTripPlannerDialog.setCanceledOnTouchOutside(true)
        addToTripPlannerDialog.setContentView(R.layout.dialog_share_profile_popup)
        addToTripPlannerDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        addToTripPlannerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addToTripPlannerDialog.show()
    }

    private fun processToLoadOtherThingsToDo() {
        adapterHealthSafetyList.addLoadingIcon()
        val param = getLoginParam()
        param["page"] = "1"
        param["page"] = adapterHealthSafetyList.getPageNumber()
        FetchItem(object : FetchItem.ListCommunicatorInterface<HealthSafetyResponse> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: HealthSafetyResponse) {
                adapterHealthSafetyList.removeLoadingIcon()
                if (adapterHealthSafetyList.getPageNumber() == "1")
                    healthSafetyCategoryList.clear()
                if (fetchedDetails.health_safety_category_list.isEmpty()) {
                    adapterHealthSafetyList.setLoadingCompleted(true)
                }
                adapterHealthSafetyList.setMetaDetails(fetchedDetails.meta)
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                textViewSafetyInfo.text = Html.fromHtml(fetchedDetails.safety_info)
                healthSafetyCategoryList.addAll(fetchedDetails.health_safety_category_list)
                setListAsPerView()
                enableSlider(fetchedDetails.slider_base_url, fetchedDetails.sliders)
            }

            override fun onFailed(message: String) {
                adapterHealthSafetyList.removeLoadingIcon()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_HEALTH_SAFETY_LIST,
            HealthSafetyResponse::class.java,
            param,
            localClassName
        )
    }

    private fun setListAsPerView() {
        tl_healthAndSafety!!.removeAllTabs()
        for (otherThingCategory in healthSafetyCategoryList) {
            tl_healthAndSafety!!.addTab(
                tl_healthAndSafety!!.newTab().setText(otherThingCategory.title)
            )
        }
        val root: View = tl_healthAndSafety.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
    }

    override fun onClick(view: View?) {
    }
}
