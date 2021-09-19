package com.mns.banzosapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.model.AmenitiesModel
import kotlinx.android.synthetic.main.row_amenities_list_item.view.*

class AmenitiesListAdapter(
    private var context: Context,
    private var amenitiesList: ArrayList<AmenitiesModel>
) :
    RecyclerView.Adapter<AmenitiesListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_amenities_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return amenitiesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.iv_amenitiesImage.setImageResource(amenitiesList[position].image!!)
        holder.itemView.tv_amenitiesName.text = amenitiesList[position].amenitiesName
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}