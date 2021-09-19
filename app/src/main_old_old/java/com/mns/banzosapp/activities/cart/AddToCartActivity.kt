package com.mns.banzosapp.activities.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.activity_add_to_cart.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class AddToCartActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)

        init()
    }

    private fun init() {
        tv_title.text = "Add To Cart"
        iv_back.setOnClickListener(this)
        btn_addToCart.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.btn_addToCart -> {
                val intent = Intent(this, ShoppingCartActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
