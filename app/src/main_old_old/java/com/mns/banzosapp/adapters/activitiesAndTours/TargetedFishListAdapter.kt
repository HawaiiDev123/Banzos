package com.mns.banzosapp.adapters.activitiesAndTours

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.activitiesAndTours.ActivityDetailActivity
import kotlinx.android.synthetic.main.row_boat_tournament_list.view.*

class TargetedFishListAdapter(private var context: Context) :
    RecyclerView.Adapter<TargetedFishListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_targeted_fish_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}