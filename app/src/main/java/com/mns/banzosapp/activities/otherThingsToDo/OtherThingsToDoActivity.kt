package com.mns.banzosapp.activities.otherThingsToDo

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.android.volley.VolleyError
import com.google.android.material.tabs.TabLayout
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.adventure.AdventureDetailActivity
import com.mns.banzosapp.adapters.AdapterOtherThingsToDo
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.CityRegionInsideDetails
import com.mns.banzosapp.model.OtherThingsToDoDetails
import com.mns.banzosapp.model.OtherThingsToDoResponse
import kotlinx.android.synthetic.main.activity_other_things_to_do.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class OtherThingsToDoActivity : AppBaseActivity(), View.OnClickListener {
    private lateinit var otherThingsListAll: MutableList<OtherThingsToDoDetails>
    private lateinit var otherThingsListForAdapter: MutableList<CityRegionInsideDetails>
    private lateinit var adapterOtherThingsToDo: AdapterOtherThingsToDo
    private var firstPosition = 0
    private var secondPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_things_to_do)
        initializeAllView()
    }

    override fun initializeAllView() {
        tv_title.text = "Other Things To Do"
        otherThingsListAll = ArrayList()
        otherThingsListForAdapter = ArrayList()
        adapterOtherThingsToDo = AdapterOtherThingsToDo(otherThingsListForAdapter)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        recyclerViewList.adapter = adapterOtherThingsToDo
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

    private fun processToLoadOtherThingsToDo() {
        showProgressDialog()
        val param = getLoginParam()
        param["page"] = "1"
        FetchItem(object : FetchItem.ListCommunicatorInterface<OtherThingsToDoResponse> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: OtherThingsToDoResponse) {
                dismissProgressDialog()
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                otherThingsListAll.clear()
                otherThingsListAll.addAll(fetchedDetails.other_things_category_list)
                setListAsPerView()
                textViewIntroductionDescription.text = Utils.fromHtml(fetchedDetails.introduction)
                enableSlider(fetchedDetails.slider_base_url, fetchedDetails.sliders)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_OTHER_THINGS_TO_DO,
            OtherThingsToDoResponse::class.java,
            param,
            localClassName
        )
    }

    private fun setListAsPerView() {
        tl_otherThings!!.removeAllTabs()
        for (otherThingCategory in otherThingsListAll) {
            tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText(otherThingCategory.title))
        }
        val root: View = tl_otherThings.getChildAt(0)
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
        iv_back.setOnClickListener(this)
        adapterOtherThingsToDo.setListener(object :
            AdapterOtherThingsToDo.OtherThingsClickListener {
            override fun onView(otherThingsToDoDetails: CityRegionInsideDetails) {
                val intent =
                    Intent(this@OtherThingsToDoActivity, AdventureDetailActivity::class.java)
                intent.putExtra("come_from", "other_things")
                startActivity(intent)
            }
        })
        tl_otherThings.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                firstPosition = tab!!.position
                tl_otherThings_2.removeAllTabs()
                val otherThingRegionList = otherThingsListAll[tab.position].region_list
                for (otherThingRegion in otherThingRegionList)
                    tl_otherThings_2!!.addTab(
                        tl_otherThings_2!!.newTab()
                            .setText(otherThingRegion.region_title + "\n" + otherThingRegion.subtitle)
                    )
                val root: View = tl_otherThings_2.getChildAt(0)
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
        tl_otherThings_2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                secondPosition = tab!!.position
                otherThingsListForAdapter.clear()
                otherThingsListForAdapter.addAll(otherThingsListAll[firstPosition].region_list[secondPosition].list)
                adapterOtherThingsToDo.notifyDataSetChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

   /* private fun init() {
        tl_otherThings!!.removeAllTabs()
        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Bars n Nightlife"))
        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Entertainment"))
        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Free things to do"))
        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Shopping"))
        tl_otherThings!!.addTab(tl_otherThings!!.newTab().setText("Tournaments"))
        val root: View = tl_otherThings.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.white))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
    }*/

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
