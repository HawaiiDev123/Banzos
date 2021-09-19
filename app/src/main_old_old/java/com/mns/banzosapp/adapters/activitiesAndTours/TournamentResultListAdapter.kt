package com.mns.banzosapp.adapters.activitiesAndTours

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R

class TournamentResultListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<TournamentResultListAdapter.MyViewHolder>() {

    private val viewTypeLabel = 1
    private val viewTypeContent = 2

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return if (viewType == viewTypeLabel) {
            MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_tournament_resullt_label_list_item, parent, false
                )
            )
        } else {
            MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_tournament_resullt_list_item, parent, false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            viewTypeLabel
        } else {
            viewTypeContent
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}