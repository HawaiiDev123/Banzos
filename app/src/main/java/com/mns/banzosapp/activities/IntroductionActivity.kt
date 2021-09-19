package com.mns.banzosapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.IntroductionMainItemsAdapter
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.IntroductionDetails
import com.mns.banzosapp.model.IntroductionSubItemsDetails
import com.mns.banzosapp.utils.Global
import kotlinx.android.synthetic.main.activity_introduction.*
import kotlinx.android.synthetic.main.activity_introduction_details.*
import kotlinx.android.synthetic.main.row_home_island_list_item.*
import kotlinx.android.synthetic.main.toolbar_layout.*

//The same activity is used for General Information screen as well.
class IntroductionActivity : AppBaseActivity(), View.OnClickListener {

    private lateinit var recyclerViewIntroductionMainItems: RecyclerView
    private lateinit var introductionSubItemsDetailsList: MutableList<IntroductionSubItemsDetails>
    private lateinit var introductionMainItemsAdapter: IntroductionMainItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        if (intent.extras != null) {
            if (intent.getStringExtra(Global.COMEFROM)?.equals("intro")!!) {
                tv_title.text = "Introduction"
            } else {
                tv_title.text = "General Information"
            }
            initializeAllView()

        }
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

        var URL: String? = null

        if (intent.getStringExtra(Global.COMEFROM)?.equals("intro")!!) {
            URL = URLHelper.FETCH_INTRODUCTION
        } else {
            URL = URLHelper.FETCH_GENERAL_INFO
        }
        showProgressDialog()
        val param = getLoginParam()
        param.put("mild_id", prefs.getIslandId().toString().trim())

        FetchItem(object : FetchItem.ListCommunicatorInterface<IntroductionDetails> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: IntroductionDetails) {
                dismissProgressDialog()
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                introductionSubItemsDetailsList.addAll(fetchedDetails.list)
                introductionMainItemsAdapter.notifyDataSetChanged()
                setData(fetchedDetails)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URL,
            IntroductionDetails::class.java,
            param,
            localClassName
        )
    }

    private fun setData(fetchedDetails: IntroductionDetails) {
        textViewIntroductionDescription.text = Html.fromHtml(fetchedDetails.introduction)
        Glide.with(this@IntroductionActivity).load(
            fetchedDetails.slider_base_url + fetchedDetails.sliders?.get(0)?.image_nm
        )
            .placeholder(R.drawable.home01)
            .into(imageViewIntroductionMain)
    }

    override fun clickListeners() {
        iv_back.setOnClickListener(this)
        introductionMainItemsAdapter.setListener(object :
            IntroductionMainItemsAdapter.IntroductionListener {
            override fun onView(introductionSubItemsDetails: IntroductionSubItemsDetails) {

                //    Log.d("URL===>",introductionSubItemsDetails.meta?.image_base_url)
                val intent =
                    Intent(this@IntroductionActivity, IntroductionDetailsActivity::class.java)
                intent.putExtra(Global.INTRODETAILS, introductionSubItemsDetails)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
