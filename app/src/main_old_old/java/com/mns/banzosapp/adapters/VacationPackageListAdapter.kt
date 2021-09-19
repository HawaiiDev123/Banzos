package com.mns.banzosapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.activitiesAndTours.ActivityListActivity
import com.mns.banzosapp.activities.beach.BeachDetailActivity
import com.mns.banzosapp.activities.lodging.LodgingListActivity
import kotlinx.android.synthetic.main.row_beach_list_item.view.*

class VacationPackageListAdapter(
    private var context: Context,
    private var comeFrom: String
) :
    RecyclerView.Adapter<VacationPackageListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_vacation_package_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}