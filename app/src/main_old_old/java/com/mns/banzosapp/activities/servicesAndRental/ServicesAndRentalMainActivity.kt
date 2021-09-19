package com.mns.banzosapp.activities.servicesAndRental

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.servicesAndRental.rental.RentalActivity
import com.mns.banzosapp.activities.servicesAndRental.valueServices.ValueServicesActivity
import kotlinx.android.synthetic.main.activity_services_and_rental_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class ServicesAndRentalMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services_and_rental_main)

        init()
    }

    private fun init() {

        tv_title.text = "Services And Rental"
        iv_back.setOnClickListener(this)

        btn_services.setOnClickListener(this)
        btn_rentals.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.btn_services -> {
                val intent = Intent(this, ValueServicesActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_rentals -> {
                val intent = Intent(this, RentalActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
