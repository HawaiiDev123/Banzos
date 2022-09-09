package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapterLoadingAndSearch
import com.mns.banzosapp.model.CityRegionInsideDetails

class AdapterWildLifeList(wildLifeDetailsList: MutableList<CityRegionInsideDetails>) :
    BaseAdapterLoadingAndSearch<CityRegionInsideDetails>(wildLifeDetailsList) {
    private lateinit var wildLifeClickListener: WildLifeClickListener
    fun setListener(wildLifeClickListener: WildLifeClickListener) {
        this.wildLifeClickListener = wildLifeClickListener
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return WildLifeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_wild_life, parent, false)
        )
    }

    override fun onBindData(
        holderBase: RecyclerView.ViewHolder,
        baseValue: CityRegionInsideDetails
    ) {
        val holder = holderBase as WildLifeViewHolder
        holder.textViewWildLifeTitle.text = baseValue.title
    }

    inner class WildLifeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewWildLifeTitle: TextView = view.findViewById(R.id.textViewWildLifeTitle)

        init {
            view.setOnClickListener {
                wildLifeClickListener.onView(getItem(adapterPosition))
            }
        }
    }

    interface WildLifeClickListener {
        fun onView(cityRegionInsideDetails: CityRegionInsideDetails)
    }
}
