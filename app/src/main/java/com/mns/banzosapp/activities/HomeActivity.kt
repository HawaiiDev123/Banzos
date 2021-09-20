package com.mns.banzosapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.AdapterIslandListHome
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.base.CallBackForRetry
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.IslandDetails
import com.mns.banzosapp.model.IslandResponse

class HomeActivity : AppBaseActivity() {

    private lateinit var recyclerViewIslandList: RecyclerView
    private lateinit var islandList: MutableList<IslandDetails>
    private lateinit var adapterIslandListHome: AdapterIslandListHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initializeAllView()
    }

    override fun initializeAllView() {
        recyclerViewIslandList = findViewById(R.id.recyclerViewIslandList)
        islandList = ArrayList()
        adapterIslandListHome = AdapterIslandListHome(islandList)
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        recyclerViewIslandList.adapter = adapterIslandListHome
        clickListeners()
        callBackForRetry = object : CallBackForRetry {
            override fun onRetry() {
                if (isOnline(callBackForRetry))
                    processToLoadIslandList()
            }
        }
        if (isOnline(callBackForRetry))
            processToLoadIslandList()
    }

    override fun clickListeners() {
        adapterIslandListHome.setListener(object : AdapterIslandListHome.IslandListener {
            override fun onView(islandDetails: IslandDetails) {
                prefs.setIslandId(islandDetails.mild_id.toString())
                val intent = Intent(this@HomeActivity, HomeCategoryActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        })
    }

    private fun processToLoadIslandList() {
        showProgressDialog()
        val param = getLoginParam()

        FetchItem(object : FetchItem.ListCommunicatorInterface<IslandResponse> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: IslandResponse) {
                dismissProgressDialog()
                URLHelper.ISLAND_IMAGE_URL = fetchedDetails.image_base_url.toString()
                islandList.addAll(fetchedDetails.islands)
                adapterIslandListHome.notifyDataSetChanged()
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.FETCH_ISLAND_LIST,
            IslandResponse::class.java,
            param,
            localClassName
        )
        /*
        * Fetch List code as an example for you (Kartik Sir)
        *
        *
        FetchList<IslandDetails>(object : FetchList.ListCommunicatorInterface {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(updatedList: List<*>) {
                dismissProgressDialog()
                islandList.addAll(updatedList as List<IslandDetails>)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchList(URLHelper.ISLAND_IMAGE_URL, IslandDetails::class.java, param, localClassName)
        * */
    }

}
