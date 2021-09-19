package com.mns.banzosapp.adapters.activitiesAndTours

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.activitiesAndTours.fishingTrip.BoatProfileDetailActivity
import kotlinx.android.synthetic.main.row_shared_fishing_list_item.view.*

class SharedFishingListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<SharedFishingListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_shared_fishing_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ll_sharedTrip.setOnClickListener {
            val intent = Intent(context, BoatProfileDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}