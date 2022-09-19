package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapterLoadingAndSearch
import com.mns.banzosapp.model.CityRegionInsideDetails

class AdapterAdventureList(adventureList: MutableList<CityRegionInsideDetails>) :

    BaseAdapterLoadingAndSearch<CityRegionInsideDetails>(adventureList) {

    private lateinit var clickInterface: ClickInterface

    fun setListener(clickInterface: ClickInterface) {
        this.clickInterface = clickInterface
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_adventure_list_item, parent, false)
        )
    }

    override fun onBindData(
        holderBase: RecyclerView.ViewHolder,
        baseValue: CityRegionInsideDetails
    ) {
        val holder=holderBase as AdapterViewHolder
        holder.tvAdventureTitle.text=baseValue.main_title
    }

    inner class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvAdventureTitle: TextView = view.findViewById(R.id.tvAdventureTitle)
        init {

            view.setOnClickListener {
                clickInterface.onClick(getItem(adapterPosition))
            }
        }
    }

    interface ClickInterface {
        fun onClick(cityRegionInsideDetails: CityRegionInsideDetails)
    }
}