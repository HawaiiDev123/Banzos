package com.mns.banzosapp.adapters.diningAndFood

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.diningFood.RestaurantDetailActivity
import kotlinx.android.synthetic.main.row_restaurants_list_item.view.*

class FarmersMarketListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<FarmersMarketListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return when (comeFrom) {
            "restaurant" -> {
                MyViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.row_restaurants_list_item, parent, false
                    )
                )
            }
            "grocery_store" -> {
                MyViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.row_grocery_stores_list_item, parent, false
                    )
                )
            }
            "farmers_market" -> {
                MyViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.row_farmers_market_list_item, parent, false
                    )
                )
            }
            else -> {
                MyViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.row_restaurants_list_item, parent, false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (comeFrom == "restaurant") {
            holder.itemView.ll_restaurant.setOnClickListener {
                val intent = Intent(context, RestaurantDetailActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}