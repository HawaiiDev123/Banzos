package com.mns.banzosapp.activities.beach

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.android.volley.VolleyError
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AdapterBestBeachesList
import com.mns.banzosapp.adapters.base.EndlessScrollNestedScrollViewListener
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.BestBeachesCategory
import com.mns.banzosapp.model.BestBeachesResponse
import com.mns.banzosapp.model.CityRegionInsideDetails
import kotlinx.android.synthetic.main.activity_beach_list.*

class BeachListActivity : AppBaseActivity(), View.OnClickListener {
    private lateinit var bestBeachesCategoryList: MutableList<BestBeachesCategory>
    private lateinit var bestBeachesDetailsList: MutableList<CityRegionInsideDetails>
    private lateinit var adapterBestBeachesList: AdapterBestBeachesList
    private var firstPosition = 0
    private var secondPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beach_list)
        updateToolbar(getString(R.string.title_best_beach))
        initializeAllView()
    }

    override fun initializeAllView() {
        bestBeachesCategoryList = ArrayList()
        bestBeachesDetailsList = ArrayList()
        adapterBestBeachesList = AdapterBestBeachesList(bestBeachesDetailsList)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        rv_beachList.adapter = adapterBestBeachesList
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadBestBeaches()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadBestBeaches()
        enableCommonSearch(object : SearchListener {
            override fun onSearch(text: String) {
                adapterBestBeachesList.filter.filter(text)
            }
        })
        clickListeners()
    }

    private fun processToLoadBestBeaches() {
        adapterBestBeachesList.addLoadingIcon()
        val param = getLoginParam()
        param["page"] = adapterBestBeachesList.getPageNumber()
        FetchItem(object : FetchItem.ListCommunicatorInterface<BestBeachesResponse> {
            override fun onError(error: VolleyError) {
                adapterBestBeachesList.removeLoadingIcon()
            }

            override fun onSuccess(fetchedDetails: BestBeachesResponse) {
                adapterBestBeachesList.removeLoadingIcon()
                if (adapterBestBeachesList.getPageNumber() == "1")
                    bestBeachesCategoryList.clear()
                if (fetchedDetails.beach_category_list.isEmpty()) {
                    adapterBestBeachesList.setLoadingCompleted(true)
                }
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                bestBeachesCategoryList.clear()
                bestBeachesCategoryList.addAll(fetchedDetails.beach_category_list)
                setListAsPerView()
                textViewDescription.text = Utils.fromHtml(fetchedDetails.introduction)
                enableSlider(fetchedDetails.slider_base_url, fetchedDetails.sliders)
            }

            override fun onFailed(message: String) {
                adapterBestBeachesList.removeLoadingIcon()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_BEST_BEACHES_LIST,
            BestBeachesResponse::class.java,
            param,
            localClassName
        )
    }

    private fun setListAsPerView() {
        tl_beachList!!.removeAllTabs()
        for (bestBeach in bestBeachesCategoryList) {
            tl_beachList!!.addTab(
                tl_beachList!!.newTab().setText(bestBeach.title)
            )
        }
        val root: View = tl_beachList.getChildAt(0)
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
        tl_beachList.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                firstPosition = tab!!.position
                tl_beachList_2.removeAllTabs()
                val otherThingRegionList = bestBeachesCategoryList[tab.position].region_list
                for (otherThingRegion in otherThingRegionList)
                    tl_beachList_2!!.addTab(
                        tl_beachList_2!!.newTab()
                            .setText(otherThingRegion.region_title + "\n" + otherThingRegion.subtitle)
                    )
                val root: View = tl_beachList_2.getChildAt(0)
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
        tl_beachList_2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                secondPosition = tab!!.position
                bestBeachesDetailsList.clear()
                bestBeachesDetailsList.addAll(bestBeachesCategoryList[firstPosition].region_list[secondPosition].list)
                adapterBestBeachesList.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        adapterBestBeachesList.setListener(object : AdapterBestBeachesList.BeachListener {
            override fun onView(cityRegionInsideDetails: CityRegionInsideDetails) {
                val intent = Intent(this@BeachListActivity, BeachDetailActivity::class.java)
                intent.putExtra(AppConstants.INTENT_BEACH_DETAILS, cityRegionInsideDetails)
                startActivity(intent)
            }
        })
        adapterBestBeachesList.setEndlessNestedScrollViewListener(nestedScrollViewBeaches,
            object : EndlessScrollNestedScrollViewListener(rv_beachList.layoutManager!!) {
                override fun onLoadMore() {
                    if (isOnline())
                        processToLoadBestBeaches()
                }
            })
    }

    override fun onClick(view: View?) {
    }
}
