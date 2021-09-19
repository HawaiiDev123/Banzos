package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.util.Util
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapter
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.common_util.Utils
import com.mns.banzosapp.model.UpcomingEventsSubDetails

class UpcomingEventsListAdapter(upcomingEventsSubDetails: MutableList<UpcomingEventsSubDetails>) :
    BaseAdapter<UpcomingEventsSubDetails>(upcomingEventsSubDetails) {

    class UpcomingEventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewUpcomingLocation: TextView = itemView.findViewById(R.id.textViewUpcomingLocation)
        val textViewUpcomingTitle: TextView = itemView.findViewById(R.id.textViewUpcomingTitle)
        val textViewUpcomingDate: TextView = itemView.findViewById(R.id.textViewUpcomingDate)
        val textViewUpcomingMonth: TextView = itemView.findViewById(R.id.textViewUpcomingMonth)
        val textViewUpcomingCounter: TextView = itemView.findViewById(R.id.textViewUpcomingCounter)

        init {
            itemView.setOnClickListener {
               // introductionListener.onView(getItem(adapterPosition))
            }
        }
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return UpcomingEventsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_upcoming_events_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindData(
        holderBase: RecyclerView.ViewHolder,
        baseValue: UpcomingEventsSubDetails
    ) {
        val holder = holderBase as UpcomingEventsViewHolder
        holder.textViewUpcomingCounter.text=(holder.adapterPosition+1).toString()
        holder.textViewUpcomingTitle.text=baseValue.title
        holder.textViewUpcomingLocation.text=baseValue.location
        holder.textViewUpcomingDate.text= Utils.dateChangeFormat(baseValue.event_date.toString(),AppConstants.ServiceFormat,AppConstants.DisplayFormatOnlyDate)
        holder.textViewUpcomingMonth.text= Utils.dateChangeFormat(baseValue.event_date.toString(),AppConstants.ServiceFormat,AppConstants.DisplayFormatOnlyMonth)

    }
}