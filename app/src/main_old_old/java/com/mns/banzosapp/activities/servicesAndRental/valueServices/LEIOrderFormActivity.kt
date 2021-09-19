package com.mns.banzosapp.activities.servicesAndRental.valueServices

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.cart.AddToCartActivity
import com.mns.banzosapp.adapters.valueServices.LEIOrderFormListAdapter
import kotlinx.android.synthetic.main.activity_l_e_i_order_form.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class LEIOrderFormActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l_e_i_order_form)

        init()
    }

    private fun init() {
        tv_title.text = "Value Services"
        iv_back.setOnClickListener(this)

        rv_price.setHasFixedSize(true)
        rv_price.layoutManager = LinearLayoutManager(this)
        rv_price.adapter = LEIOrderFormListAdapter(this, "")

        btn_addToCart.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.btn_addToCart -> {
                val intent = Intent(this, AddToCartActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
