package com.mns.banzosapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R

class MostPopComboDetailListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<MostPopComboDetailListAdapter.MyViewHolder>() {

    private val viewTypeLabel = 1
    private val viewTypeContent = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return if (viewType == viewTypeLabel) {
            MyViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.row_most_popular_combo_detail_label, parent,
                    false
                )
            )
        } else {
            MyViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.row_most_popular_combo_detail_list_item, parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            viewTypeLabel
        } else {
            viewTypeContent
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}