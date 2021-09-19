package com.mns.banzosapp.adapters.activitiesAndTours

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.activitiesAndTours.ActivityDetailActivity
import com.mns.banzosapp.activities.cart.AddToCartActivity
import com.mns.banzosapp.activities.gift.GiveAsGiftActivity
import kotlinx.android.synthetic.main.row_activities_list_item.view.*

class ActivitiesListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<ActivitiesListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_activities_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ll_activities.setOnClickListener {
            val intent = Intent(context, ActivityDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
        holder.itemView.tv_giveAsGift.setOnClickListener {
            val intent = Intent(context, GiveAsGiftActivity::class.java)
            context.startActivity(intent)
        }
        holder.itemView.tv_addToCart.setOnClickListener {
            val intent = Intent(context, AddToCartActivity::class.java)
            context.startActivity(intent)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}