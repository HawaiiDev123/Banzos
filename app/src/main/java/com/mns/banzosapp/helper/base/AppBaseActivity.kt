package com.mns.banzosapp.helper.base

abstract class AppBaseActivity : BaseActivity() {

    fun updateToolbar(title: String) {
        /*val textViewToolbarTitle: TextView = findViewById(R.id.textViewToolbarTitle)
        textViewToolbarTitle.text = title
        findViewById<ImageView>(R.id.imageViewBack).setOnClickListener {
            onBackPressed()
        }*/
    }

    fun getParam(): HashMap<String, String> {
        return HashMap()
    }

    fun getLoginParam(): HashMap<String, String> {
        val param = HashMap<String, String>()
        /*param["customer_id"] = prefs.getUserId()!!
        param["Authorization"] = "Bearer " + prefs.getUserToken()!!*/
        return param
    }

    abstract fun initializeAllView()
    abstract fun setListsAndAdapters()
    abstract fun clickListeners()

}