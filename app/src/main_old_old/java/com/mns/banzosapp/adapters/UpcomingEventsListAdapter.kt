package com.mns.banzosapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.callbacks.AddToTripPlannerCallback
import kotlinx.android.synthetic.main.row_fav_trips_list_item.view.*

class UpcomingEventsListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<UpcomingEventsListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_upcoming_events_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}