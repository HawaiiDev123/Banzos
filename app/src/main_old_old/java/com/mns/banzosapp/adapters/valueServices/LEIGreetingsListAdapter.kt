package com.mns.banzosapp.adapters.valueServices

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.servicesAndRental.valueServices.LEIOrderFormActivity
import kotlinx.android.synthetic.main.row_lei_greetings_list_item.view.*

class LEIGreetingsListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<LEIGreetingsListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_lei_greetings_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.tv_order.setOnClickListener {
            val intent=Intent(context, LEIOrderFormActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}