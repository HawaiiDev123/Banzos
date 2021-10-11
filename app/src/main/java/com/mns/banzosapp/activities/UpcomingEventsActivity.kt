package com.mns.banzosapp.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.VolleyError
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.UpcomingEventsListAdapter
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.UpcomingEventDetails
import com.mns.banzosapp.model.UpcomingEventsSubDetails
import kotlinx.android.synthetic.main.activity_upcoming_events.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class UpcomingEventsActivity : AppBaseActivity(), View.OnClickListener {


    private lateinit var upcomingEventDetailsList: MutableList<UpcomingEventsSubDetails>
    private lateinit var upcomingEventsListAdapter: UpcomingEventsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming_events)

        initializeAllView()
    }

    override fun initializeAllView() {
        tv_title.text = getString(R.string.title_upcoming_events)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {

        rv_upcomingEvents.setHasFixedSize(true)
        upcomingEventDetailsList=ArrayList()
        rv_upcomingEvents.layoutManager = LinearLayoutManager(this)
        upcomingEventsListAdapter = UpcomingEventsListAdapter(upcomingEventDetailsList)
        rv_upcomingEvents.adapter = upcomingEventsListAdapter
        clickListeners()
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadUpcomingEventList()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadUpcomingEventList()
    }

    private fun processToLoadUpcomingEventList() {
        showProgressDialog()
        val param = getLoginParam()

        FetchItem(object : FetchItem.ListCommunicatorInterface<UpcomingEventDetails> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: UpcomingEventDetails) {
                dismissProgressDialog()
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.meta?.image_base_url.toString()
                upcomingEventDetailsList.addAll(fetchedDetails.list!!)
                upcomingEventsListAdapter.notifyDataSetChanged()
                setData(fetchedDetails)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_UPCOMING_EVENT,
            UpcomingEventDetails::class.java,
            param,
            localClassName
        )
    }

    private fun setData(fetchedDetails: UpcomingEventDetails) {
        textViewUpcomingTitle.text = fetchedDetails.title
        textViewUpcomingDescription.text = fetchedDetails.description
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
