package com.mns.banzosapp.activities.combo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.activity_combo_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class ComboMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combo_main)

        init()
    }

    private fun init() {

        tv_title.text = "Combo Trips"
        iv_back.setOnClickListener(this)

        btn_mostPopular.setOnClickListener(this)
        btn_createYourOwn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.btn_mostPopular -> {
                val intent = Intent(this, MostPopularComboListActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_createYourOwn -> {
                val intent = Intent(this, CreateOwnComboActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
