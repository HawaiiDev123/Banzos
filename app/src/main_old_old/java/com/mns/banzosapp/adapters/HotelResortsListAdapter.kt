package com.mns.banzosapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.lodging.LodgingDetailActivity
import kotlinx.android.synthetic.main.row_hotel_resort_list_item.view.*

class HotelResortsListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<HotelResortsListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_hotel_resort_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ll_hotelResorts.setOnClickListener {
            val intent = Intent(context, LodgingDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}