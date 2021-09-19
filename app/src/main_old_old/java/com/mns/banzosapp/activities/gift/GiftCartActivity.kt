package com.mns.banzosapp.activities.gift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.toolbar_layout.*

class GiftCartActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift_cart)

        init()
    }

    private fun init(){
        tv_title.text = "Gift Card"
        iv_back.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id)
        {
            R.id.iv_back->{
                finish()
            }
        }
    }
}
