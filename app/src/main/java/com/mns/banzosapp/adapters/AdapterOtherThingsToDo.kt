package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapter
import com.mns.banzosapp.model.CityRegionInsideDetails

class AdapterOtherThingsToDo(otherThingsList: MutableList<CityRegionInsideDetails>) :
    BaseAdapter<CityRegionInsideDetails>(otherThingsList) {
    private lateinit var otherThingsClickListener: OtherThingsClickListener
    fun setListener(otherThingsClickListener: OtherThingsClickListener) {
        this.otherThingsClickListener = otherThingsClickListener
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return OtherThingsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_other_things_to_do, parent, false)
        )
    }

    override fun onBindData(
        holderBase: RecyclerView.ViewHolder,
        baseValue: CityRegionInsideDetails
    ) {
        val holder = holderBase as OtherThingsViewHolder
        holder.textViewTitle.text = baseValue.main_title
    }

    inner class OtherThingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)

        init {
            itemView.setOnClickListener {
                otherThingsClickListener.onView(getItem(adapterPosition))
            }
        }
    }

    interface OtherThingsClickListener {
        fun onView(otherThingsToDoDetails: CityRegionInsideDetails)
    }
}
