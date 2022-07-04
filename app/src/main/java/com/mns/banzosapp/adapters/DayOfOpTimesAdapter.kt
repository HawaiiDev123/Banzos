package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapter
import com.mns.banzosapp.model.TimeDetails

class DayOfOpTimesAdapter(timeDetailsList: MutableList<TimeDetails>) :
    BaseAdapter<TimeDetails>(timeDetailsList) {

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TimesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rows_times_item_list, parent, false)
        )
    }

    override fun onBindData(holderBase: RecyclerView.ViewHolder, baseValue: TimeDetails) {

        val holder = holderBase as TimesViewHolder
        holder.tvPOIDTimesItemDay.text = baseValue.day
        if (baseValue.is_closed == 1) {
            holder.tvPOIDTimesItemStartEndTime.text = "Closed"
        } else {
            val startEnd: String = baseValue.day_start + "-" + baseValue.day_end
            holder.tvPOIDTimesItemStartEndTime.text = startEnd
        }


    }

    inner class TimesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvPOIDTimesItemDay: TextView = itemView.findViewById(R.id.tvPOIDTimesItemDay)
        val tvPOIDTimesItemStartEndTime: TextView =
            itemView.findViewById(R.id.tvPOIDTimesItemStartEndTime)


    }

}