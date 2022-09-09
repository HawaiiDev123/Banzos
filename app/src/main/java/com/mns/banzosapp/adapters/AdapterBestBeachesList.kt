package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapterLoadingAndSearch
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.model.CityRegionInsideDetails

class AdapterBestBeachesList(bestBeachesDetailsList: MutableList<CityRegionInsideDetails>) :
    BaseAdapterLoadingAndSearch<CityRegionInsideDetails>(bestBeachesDetailsList) {
    private lateinit var beachListener: BeachListener
    fun setListener(beachListener: BeachListener) {
        this.beachListener = beachListener
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return BeachesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_beach_list_item, parent, false)
        )
    }

    override fun onBindData(
        holderBase: RecyclerView.ViewHolder,
        baseValue: CityRegionInsideDetails
    ) {
        val holder = holderBase as BeachesViewHolder
        holder.textViewBeachName.text = baseValue.main_title
        if (baseValue.image.isNotEmpty())
            Glide.with(context).load(URLHelper.ISLAND_IMAGE_URL + baseValue.image[0].image_nm)
                .placeholder(R.drawable.ic_sample1).into(holder.imageViewBeachImage)
    }

    inner class BeachesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewBeachImage: ImageView = view.findViewById(R.id.imageViewBeachImage)
        val textViewBeachName: TextView = view.findViewById(R.id.textViewBeachName)

        init {
            view.setOnClickListener {
                beachListener.onView(getItem(adapterPosition))
            }
        }
    }

    interface BeachListener {
        fun onView(cityRegionInsideDetails: CityRegionInsideDetails)
    }
}
