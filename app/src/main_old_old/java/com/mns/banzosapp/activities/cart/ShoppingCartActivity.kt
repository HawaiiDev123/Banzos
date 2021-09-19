package com.mns.banzosapp.activities.cart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.CartListAdapter
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class ShoppingCartActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        init()
    }

    private fun init() {
        tv_title.text = "Shopping Cart"
        iv_back.setOnClickListener(this)

        rv_cartList.setHasFixedSize(true)
        rv_cartList.layoutManager=LinearLayoutManager(this)
        rv_cartList.adapter=CartListAdapter(this,"")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
