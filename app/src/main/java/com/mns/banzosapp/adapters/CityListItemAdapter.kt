package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapter
import com.mns.banzosapp.model.CityRegionInsideDetails

class CityListItemAdapter(cityDetailsList: MutableList<CityRegionInsideDetails>) :
    BaseAdapter<CityRegionInsideDetails>(cityDetailsList) {

    private lateinit var cityItemListener: CityItemListener

    fun setListener(cityItemListener: CityItemListener) {
        this.cityItemListener = cityItemListener
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CityMainListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_city_name_list, parent, false)
        )
    }

    override fun onBindData(
        holderBase: RecyclerView.ViewHolder,
        baseValue: CityRegionInsideDetails
    ) {
            val holder=holderBase as CityMainListViewHolder
            holder.textViewCityNameTitle.text=baseValue.main_title
    }

    inner class CityMainListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCityNameTitle: TextView = itemView.findViewById(R.id.textViewCityNameTitle)

        init {
            itemView.setOnClickListener {
                cityItemListener.onClick(getItem(adapterPosition))
            }
        }
    }

    interface CityItemListener {
        fun onClick(cityRegionInsideDetails: CityRegionInsideDetails)
    }
}