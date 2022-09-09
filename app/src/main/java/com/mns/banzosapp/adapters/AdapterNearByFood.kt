package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapter
import com.mns.banzosapp.model.BeachInsideDetails

class AdapterNearByFood(nearbyPlaces: MutableList<BeachInsideDetails>) :
    BaseAdapter<BeachInsideDetails>(nearbyPlaces) {
    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NearbyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_nearby_food, parent, false)
        )
    }

    override fun onBindData(holderBase: RecyclerView.ViewHolder, baseValue: BeachInsideDetails) {
        val holder = holderBase as NearbyViewHolder
        holder.textViewFoodName.text = baseValue.title
        holder.textViewFoodPlace.text = baseValue.sub_title
        holder.textViewMiles.text = baseValue.mile
        holder.textViewFoodName.text = baseValue.title
        Glide.with(context).load(baseValue.image[0].image_nm)
            .placeholder(R.drawable.ic_sample1).fitCenter().into(holder.imageViewFood)
    }

    inner class NearbyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewFood: ImageView = view.findViewById(R.id.imageViewFood)
        val textViewFoodName: TextView = view.findViewById(R.id.textViewFoodName)
        val textViewFoodPlace: TextView = view.findViewById(R.id.textViewFoodPlace)
        val textViewMiles: TextView = view.findViewById(R.id.textViewMiles)
        val textViewProfile: TextView = view.findViewById(R.id.textViewProfile)

        init {
        }
    }
}
