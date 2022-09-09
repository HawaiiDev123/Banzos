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
import com.willy.ratingbar.ScaleRatingBar

class AdapterNearByPlaces(nearbyPlaces: MutableList<BeachInsideDetails>) :
    BaseAdapter<BeachInsideDetails>(nearbyPlaces) {
    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NearbyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_nearby_places, parent, false)
        )
    }

    override fun onBindData(holderBase: RecyclerView.ViewHolder, baseValue: BeachInsideDetails) {
        val holder = holderBase as NearbyViewHolder
        holder.textViewNearByPlaceName.text = baseValue.title
        holder.ratingBarRating.rating = baseValue.rating_star?.toFloat() ?: 0f
        holder.textViewReviews.text = "${baseValue.rating} Reviews"
        holder.textViewMiles.text = baseValue.mile
        holder.textViewNearByPlaceDetails.text = baseValue.sub_title
        Glide.with(context).load(baseValue.image[0].image_nm)
            .placeholder(R.drawable.ic_sample1).fitCenter().into(holder.imageViewNearbyPlace)
    }

    inner class NearbyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewNearbyPlace: ImageView = view.findViewById(R.id.imageViewNearbyPlace)
        val textViewNearByPlaceName: TextView = view.findViewById(R.id.textViewNearByPlaceName)
        val textViewNearByPlaceDetails: TextView =
            view.findViewById(R.id.textViewNearByPlaceDetails)
        val textViewMiles: TextView = view.findViewById(R.id.textViewMiles)
        val textViewReviews: TextView = view.findViewById(R.id.textViewReviews)
        val ratingBarRating: ScaleRatingBar = view.findViewById(R.id.ratingBarRating)

        init {
        }
    }
}
