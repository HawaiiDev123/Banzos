package com.mns.banzosapp.activities.wildlife

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.android.volley.VolleyError
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AdapterWildLifeList
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.CityRegionInsideDetails
import com.mns.banzosapp.model.WildlifeCategory
import com.mns.banzosapp.model.WildlifeResponse
import kotlinx.android.synthetic.main.activity_wildlife.*

class WildlifeActivity : AppBaseActivity(), View.OnClickListener {
    private lateinit var wildLifeCategoryList: MutableList<WildlifeCategory>
    private lateinit var wildLifeDetailsList: MutableList<CityRegionInsideDetails>
    private lateinit var adapterWildLifeList: AdapterWildLifeList
    private var firstPosition = 0
    private var secondPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wildlife)
        updateToolbar(getString(R.string.title_wildlife))
        initializeAllView()
    }

    override fun initializeAllView() {
        wildLifeCategoryList = ArrayList()
        wildLifeDetailsList = ArrayList()
        adapterWildLifeList = AdapterWildLifeList(wildLifeDetailsList)
        setListsAndAdapters()
    }

    private fun processToLoadWildLife() {
        showProgressDialog()
        val param = getLoginParam()
        FetchItem(object : FetchItem.ListCommunicatorInterface<WildlifeResponse> {
            override fun onError(error: VolleyError) {
                dismissProgressDialog()
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: WildlifeResponse) {
                dismissProgressDialog()
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                wildLifeCategoryList.clear()
                wildLifeCategoryList.addAll(fetchedDetails.wildlife_category)
                setListAsPerView()
                textViewWildlifeDescription.text = Utils.fromHtml(fetchedDetails.introduction)
                enableSlider(fetchedDetails.slider_base_url, fetchedDetails.sliders)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_WILD_LIFE,
            WildlifeResponse::class.java,
            param,
            localClassName
        )
    }

    private fun setListAsPerView() {
        tl_wildlife!!.removeAllTabs()
        for (bestBeach in wildLifeCategoryList) {
            tl_wildlife!!.addTab(
                tl_wildlife!!.newTab().setText(bestBeach.title)
            )
        }
        val root: View = tl_wildlife.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
    }

    override fun setListsAndAdapters() {
        rv_wildlife.adapter = adapterWildLifeList
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadWildLife()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadWildLife()
        enableCommonSearch(object : SearchListener {
            override fun onSearch(text: String) {
                adapterWildLifeList.filter.filter(text)
            }
        })
        clickListeners()
    }

    override fun clickListeners() {
        tl_wildlife.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tl_wildlife_2.visibility = View.GONE
                firstPosition = tab!!.position
                tl_wildlife_2.removeAllTabs()
                val otherThingRegionList = wildLifeCategoryList[tab.position].fish_type
                if (otherThingRegionList != null) {
                    tl_wildlife_2.visibility = View.VISIBLE
                    for (otherThingRegion in otherThingRegionList)
                        tl_wildlife_2!!.addTab(
                            tl_wildlife_2!!.newTab()
                                .setText(otherThingRegion.sub_title)
                        )
                    val root: View = tl_wildlife_2.getChildAt(0)
                    if (root is LinearLayout) {
                        root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
                        val drawable = GradientDrawable()
                        drawable.setColor(resources.getColor(R.color.transparent))
                        drawable.setSize(2, 1)
                        root.dividerPadding = 25
                        root.dividerDrawable = drawable
                    }
                } else {
                    tl_wildlife_2.visibility = View.GONE
                    wildLifeDetailsList.clear()
                    wildLifeDetailsList.addAll(wildLifeCategoryList[firstPosition].list)
                    adapterWildLifeList.notifyDataSetChanged()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        tl_wildlife_2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                secondPosition = tab!!.position
                wildLifeDetailsList.clear()
                wildLifeDetailsList.addAll(wildLifeCategoryList[firstPosition].fish_type!![secondPosition].sub_list)
                adapterWildLifeList.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        adapterWildLifeList.setListener(object : AdapterWildLifeList.WildLifeClickListener {
            override fun onView(cityRegionInsideDetails: CityRegionInsideDetails) {
                val intent = Intent(this@WildlifeActivity, WildlifeDetailActivity::class.java)
                intent.putExtra(AppConstants.INTENT_WILDLIFE_DETAILS, cityRegionInsideDetails)
                startActivity(intent)
            }
        })
    }

    override fun onClick(view: View?) {
    }
}
