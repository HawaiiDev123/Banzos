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

class AdapterHealthSafetyList(healthSafetyDetailsList: MutableList<CityRegionInsideDetails>) :
    BaseAdapterLoadingAndSearch<CityRegionInsideDetails>(healthSafetyDetailsList) {
    private lateinit var healthSafetyClickListener: HealthSafetyClickListener
    fun setListener(healthSafetyClickListener: HealthSafetyClickListener) {
        this.healthSafetyClickListener = healthSafetyClickListener
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return HealthSafetyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_health_and_safety_list_item, parent, false)
        )
    }

    override fun onBindData(
        holderBase: RecyclerView.ViewHolder,
        baseValue: CityRegionInsideDetails
    ) {
        val holder = holderBase as HealthSafetyViewHolder
        holder.textViewMainTitle.text = baseValue.main_title
        holder.textViewAddress.text = baseValue.address
        holder.textViewPhoneNumber.text = baseValue.phone_no
        holder.textViewMiles.text = baseValue.nearby_milemaker
        Glide.with(context).load(URLHelper.ISLAND_IMAGE_URL + baseValue.image[0].image_nm)
            .placeholder(R.drawable.ic_sample3).into(holder.iv_activityImage)
    }

    inner class HealthSafetyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_activityImage: ImageView = itemView.findViewById(R.id.iv_activityImage)
        val textViewMainTitle: TextView = itemView.findViewById(R.id.textViewMainTitle)
        val textViewAddress: TextView = itemView.findViewById(R.id.textViewAddress)
        val textViewPhoneNumber: TextView = itemView.findViewById(R.id.textViewPhoneNumber)
        val textViewMiles: TextView = itemView.findViewById(R.id.textViewMiles)
        val textViewCall: TextView = itemView.findViewById(R.id.textViewCall)
        val textViewShare: TextView = itemView.findViewById(R.id.textViewShare)

        init {
            textViewShare.setOnClickListener {
                healthSafetyClickListener.onShare(getItem(adapterPosition))
            }
            textViewCall.setOnClickListener {
                healthSafetyClickListener.onCall(getItem(adapterPosition))
            }
        }
    }

    interface HealthSafetyClickListener {
        fun onShare(healthSafetyDetails: CityRegionInsideDetails)
        fun onCall(healthSafetyDetails: CityRegionInsideDetails)
    }
}
