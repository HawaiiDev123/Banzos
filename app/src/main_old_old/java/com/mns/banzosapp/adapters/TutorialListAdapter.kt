package com.mns.banzosapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.model.TutorialListModel
import kotlinx.android.synthetic.main.row_tutorial_item.view.*

class TutorialListAdapter(
    private var context: Context,
    private var tutorialList: ArrayList<TutorialListModel>
) :
    RecyclerView.Adapter<TutorialListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_tutorial_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return tutorialList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.iv_tutorialImage.setImageResource(tutorialList.get(position).image!!)
        holder.itemView.tv_tutorialDesc.text = tutorialList[position].desc!!

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}