package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapter
import com.mns.banzosapp.model.BeachInsideDetails

class AdapterOtherThingsToDoBeach(otherThinkingToDoNearby: MutableList<BeachInsideDetails>) :
    BaseAdapter<BeachInsideDetails>(otherThinkingToDoNearby) {
    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return OtherThingsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_nearby_other_things_to_do_beach, parent, false)
        )
    }

    override fun onBindData(holderBase: RecyclerView.ViewHolder, baseValue: BeachInsideDetails) {
        val holder = holderBase as OtherThingsViewHolder
        holder.textViewOtherThingsToDoName.text = baseValue.title
        holder.textViewOtherThingsToDoPrice.text = "Your Price : ${baseValue.price}"
        Glide.with(context).load(baseValue.image[0].image_nm)
            .placeholder(R.drawable.ic_sample1).fitCenter().into(holder.imageViewOtherThingsToDo)
    }

    inner class OtherThingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewOtherThingsToDoName: TextView =
            view.findViewById(R.id.textViewOtherThingsToDoName)
        val textViewOtherThingsToDoPrice: TextView =
            view.findViewById(R.id.textViewOtherThingsToDoPrice)
        val imageViewOtherThingsToDo: ImageView =
            view.findViewById(R.id.imageViewOtherThingsToDo)

        init {
        }
    }
}
