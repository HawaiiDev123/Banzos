package com.mns.banzosapp.adapters.diningAndFood

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R

class RestaurantReviewsListAdapter(private var context: Context) :
    RecyclerView.Adapter<RestaurantReviewsListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_restaurant_reviews_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}