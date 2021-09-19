package com.mns.banzosapp.activities.combo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.CreateOwnComboListAdapter
import com.mns.banzosapp.utils.MiddleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_create_own_combo.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class CreateOwnComboActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_own_combo)
        init()
    }

    private fun init() {

        tv_title.text = "Create your own Combo"
        iv_back.setOnClickListener(this)

        rv_oceanActivities.setHasFixedSize(true)
        rv_oceanActivities.layoutManager = LinearLayoutManager(this)
        rv_oceanActivities.adapter = CreateOwnComboListAdapter(this, "")
        rv_oceanActivities.addItemDecoration(MiddleDividerItemDecoration(this,
                MiddleDividerItemDecoration.HORIZONTAL))

        rv_islandActivities.setHasFixedSize(true)
        rv_islandActivities.layoutManager = LinearLayoutManager(this)
        rv_islandActivities.adapter = CreateOwnComboListAdapter(this, "")
        rv_islandActivities.addItemDecoration(MiddleDividerItemDecoration(this,
            MiddleDividerItemDecoration.HORIZONTAL))
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_back -> {
                finish()
            }
        }
    }
}
