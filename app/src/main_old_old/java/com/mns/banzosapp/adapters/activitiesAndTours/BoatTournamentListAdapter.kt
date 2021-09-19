package com.mns.banzosapp.adapters.activitiesAndTours

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.activitiesAndTours.ActivityDetailActivity
import kotlinx.android.synthetic.main.row_boat_tournament_list.view.*

class BoatTournamentListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<BoatTournamentListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_boat_tournament_list, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}