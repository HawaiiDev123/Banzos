package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapter
import com.mns.banzosapp.model.IntroductionSubItemsDetails

class IntroductionMainItemsAdapter(introductionSubItemsDetailsList: MutableList<IntroductionSubItemsDetails>) :
    BaseAdapter<IntroductionSubItemsDetails>(introductionSubItemsDetailsList) {

    private lateinit var introductionListener: IntroductionListener;

    fun setListener(introductionListener: IntroductionListener) {
        this.introductionListener = introductionListener
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return IntroductionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_introduction_sub_items, parent, false)
        )
    }

    override fun onBindData(
        holderBase: RecyclerView.ViewHolder,
        baseValue: IntroductionSubItemsDetails
    ) {
        val holder = holderBase as IntroductionViewHolder
        holder.textViewSubItemsTitle.text = baseValue.title

    }

    inner class IntroductionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewSubItemsTitle: TextView = itemView.findViewById(R.id.textViewSubItemsTitle)

        init {
            itemView.setOnClickListener {
                    introductionListener.onView(getItem(adapterPosition))
            }
        }
    }

    interface IntroductionListener {
        fun onView(introductionSubItemsDetails: IntroductionSubItemsDetails)
    }
}