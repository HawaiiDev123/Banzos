package com.mns.banzosapp.activities.adventure

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.android.volley.VolleyError
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AdapterAdventureList
import com.mns.banzosapp.adapters.base.EndlessScrollNestedScrollViewListener
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.AdventureCategory
import com.mns.banzosapp.model.AdventureMainResponse
import com.mns.banzosapp.model.CityRegionInsideDetails
import kotlinx.android.synthetic.main.activity_adventure.*
import kotlinx.android.synthetic.main.activity_adventure.textViewDescription
import kotlinx.android.synthetic.main.activity_beach_list.*

class AdventureActivity : AppBaseActivity(), View.OnClickListener {

    private lateinit var adventureCategoryList: MutableList<AdventureCategory>
    private lateinit var adventureDetails: MutableList<CityRegionInsideDetails>
    private lateinit var adapterAdventureList: AdapterAdventureList
    private var firstPosition = 0
    private var secondPosition = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adventure)
        updateToolbar(getString(R.string.menu_adventures))
        initializeAllView()
    }

    override fun initializeAllView() {
        adventureCategoryList = ArrayList()
        adventureDetails = ArrayList()
        adapterAdventureList = AdapterAdventureList(adventureDetails)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {

        rv_adventureList.adapter = adapterAdventureList
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadAdapterData()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadAdapterData()

        enableCommonSearch(object : SearchListener {
            override fun onSearch(text: String) {
                adapterAdventureList.filter.filter(text)
            }
        })
        clickListeners()
    }

    private fun processToLoadAdapterData() {

        adapterAdventureList.addLoadingIcon()
        val param = getLoginParam()
        param["page"] = adapterAdventureList.getPageNumber()
        FetchItem(object : FetchItem.ListCommunicatorInterface<AdventureMainResponse> {
            override fun onError(error: VolleyError) {
                adapterAdventureList.removeLoadingIcon()
            }

            override fun onSuccess(fetchedDetails: AdventureMainResponse) {
                adapterAdventureList.removeLoadingIcon()
                if (adapterAdventureList.getPageNumber() == "1")
                    adventureCategoryList.clear()
                if (fetchedDetails.adventure_category_list.isEmpty()) {
                    adapterAdventureList.setLoadingCompleted(true)
                }
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                adventureCategoryList.clear()
                adventureCategoryList.addAll(fetchedDetails.adventure_category_list)
                setListAsPerView()
                textViewDescription.text = Utils.fromHtml(fetchedDetails.introduction)
                enableSlider(fetchedDetails.slider_base_url, fetchedDetails.sliders)
            }

            override fun onFailed(message: String) {
                adapterAdventureList.removeLoadingIcon()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_ADVENTURE_LIST,
            AdventureMainResponse::class.java,
            param,
            localClassName
        )


    }

    private fun setListAsPerView() {
        tl_adventure!!.removeAllTabs()
        for (adventure in adventureCategoryList) {
            tl_adventure!!.addTab(
                tl_adventure!!.newTab().setText(adventure.title)
            )
        }
        val root: View = tl_adventure.getChildAt(0)
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
        tl_adventure.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                firstPosition = tab!!.position
                tl_adventure_2.removeAllTabs()
                val otherThingRegionList = adventureCategoryList[tab.position].region_list
                for (otherThingRegion in otherThingRegionList)
                    tl_adventure_2!!.addTab(
                        tl_adventure_2!!.newTab()
                            .setText(otherThingRegion.region_title + "\n" + otherThingRegion.subtitle)
                    )
                val root: View = tl_adventure_2.getChildAt(0)
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
        tl_adventure_2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                secondPosition = tab!!.position
                adventureDetails.clear()
                adventureDetails.addAll(adventureCategoryList[firstPosition].region_list[secondPosition].list)
                adapterAdventureList.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        adapterAdventureList.setListener(object : AdapterAdventureList.ClickInterface {
            override fun onClick(cityRegionInsideDetails: CityRegionInsideDetails) {
                val intent = Intent(this@AdventureActivity, AdventureDetailActivity::class.java)
                intent.putExtra(AppConstants.INTENT_ADVENTURE_ITEM, cityRegionInsideDetails)
                startActivity(intent)
            }
        })
        adapterAdventureList.setEndlessNestedScrollViewListener(nestedScrollViewAdventure,
            object : EndlessScrollNestedScrollViewListener(rv_adventureList.layoutManager!!) {
                override fun onLoadMore() {
                    if (isOnline())
                        processToLoadAdapterData()
                }
            })
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            /* R.id.cv_adventure -> {
                 val intent = Intent(this, AdventureDetailActivity::class.java)
                 intent.putExtra("come_from", "adventure")
                 startActivity(intent)
             }*/
        }
    }
}
