package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapter
import com.mns.banzosapp.model.ActivityDetails

class AdapterOtherActivityList(otherActivityDetailsList: MutableList<ActivityDetails>) :
    BaseAdapter<ActivityDetails>(otherActivityDetailsList) {
    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return OtherActivityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_other_activities_list_item, parent, false)
        )
    }

    override fun onBindData(holderBase: RecyclerView.ViewHolder, baseValue: ActivityDetails) {
        val holder = holderBase as OtherActivityViewHolder
    }

    inner class OtherActivityViewHolder(iView: View) : RecyclerView.ViewHolder(iView) {
        init {
        }
    }
}
