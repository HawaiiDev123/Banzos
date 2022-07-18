package com.mns.banzosapp.helper.base

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.model.base.CommonSliderDetails
import kotlinx.android.synthetic.main.common_search_layout.*
import kotlinx.android.synthetic.main.common_slider_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.*

abstract class AppBaseActivity : BaseActivity() {
    private lateinit var searchListener: SearchListener
    fun updateToolbar(title: String) {
        tv_title.text = title
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            onBackPressed()
        }
    }

    fun enableSlider(base_url: String?, sliders: List<CommonSliderDetails>) {
        carouselViewSlider.setImageListener { position, imageView ->
            Glide.with(context).load(base_url + sliders[position].image_nm)
                .placeholder(R.drawable.ic_sample5).into(imageView)
        }
        carouselViewSlider.pageCount = sliders.size
    }

    fun enableCommonSearch(searchListenerCurrent: SearchListener) {
        searchListener = searchListenerCurrent
        frameLayoutCommonSearch.visibility = View.VISIBLE
        editTextCommonSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                searchListener.onSearch(getEditTextData(editTextCommonSearch))
            }
        })
    }

    fun getParam(): HashMap<String, String> {
        return HashMap()
    }

    fun getLoginParam(): HashMap<String, String> {
        val param = HashMap<String, String>()
        param["mild_id"] = prefs.getIslandId().trim()
        param["Authorization"] = "Bearer " + prefs.getUserToken()!!
        /*param["customer_id"] = prefs.getUserId()!!
        param["Authorization"] = "Bearer " + prefs.getUserToken()!!*/
        return param
    }

    interface SearchListener {
        fun onSearch(text: String)
    }

    abstract fun initializeAllView()
    abstract fun setListsAndAdapters()
    abstract fun clickListeners()
}