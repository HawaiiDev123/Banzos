package com.mns.banzosapp.helper.base

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.model.base.CommonSliderDetails
import kotlinx.android.synthetic.main.common_slider_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.*

abstract class AppBaseActivity : BaseActivity() {

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

    fun getParam(): HashMap<String, String> {
        return HashMap()
    }

    fun getLoginParam(): HashMap<String, String> {
        val param = HashMap<String, String>()
        if (prefs.getIslandId() != null) {
            param["mild_id"] = prefs.getIslandId().toString().trim()
        }
        /*param["customer_id"] = prefs.getUserId()!!
        param["Authorization"] = "Bearer " + prefs.getUserToken()!!*/
        return param
    }

    abstract fun initializeAllView()
    abstract fun setListsAndAdapters()
    abstract fun clickListeners()

}