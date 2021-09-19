package com.mns.banzosapp.activities.combo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.cart.AddToCartActivity
import com.mns.banzosapp.activities.gift.GiveAsGiftActivity
import com.mns.banzosapp.adapters.MostPopComboDetailListAdapter
import com.mns.banzosapp.utils.MiddleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_most_popular_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MostPopularDetailActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_most_popular_detail)

        init()
    }

    private fun init() {

        tv_title.text = "Combo Most Popular"
        iv_back.setOnClickListener(this)

        rv_mostPopComboList.setHasFixedSize(true)
        rv_mostPopComboList.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        rv_mostPopComboList.adapter = MostPopComboDetailListAdapter(this, "")
        rv_mostPopComboList.addItemDecoration(
            MiddleDividerItemDecoration(
                this,
                MiddleDividerItemDecoration.ALL
            )
        )

        tv_giveAsGift.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.tv_giveAsGift->{
                val intent = Intent(this, GiveAsGiftActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_addToCart->{
                val intent = Intent(this, AddToCartActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
