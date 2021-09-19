package com.mns.banzosapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.callbacks.AddToTripPlannerCallback
import kotlinx.android.synthetic.main.row_fav_trips_list_item.view.*

class FavTripListAdapter(private var context: Context, private var comeFrom: String,
    private var addToTripPlannerCallback: AddToTripPlannerCallback
) : RecyclerView.Adapter<FavTripListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_fav_trips_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        when (comeFrom) {
            "fav_trip" -> {
                holder.itemView.btn_tripPlanner.setText("Trip Planner")
                holder.itemView.btn_bookNow.setText("Book Now")
            }
            "recently_viewed" -> {
                holder.itemView.btn_tripPlanner.setText("Book Now")
                holder.itemView.btn_bookNow.setText("Cancel")
            }
        }
        holder.itemView.btn_tripPlanner.setOnClickListener {
            if (comeFrom.equals("fav_trip", true))
                addToTripPlannerCallback.addToTripPlanner()
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}