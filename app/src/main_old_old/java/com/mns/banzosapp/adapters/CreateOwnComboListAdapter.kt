package com.mns.banzosapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R

class CreateOwnComboListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<CreateOwnComboListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_create_own_combo_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /*holder.itemView.ll_combo.setOnClickListener {
            val intent = Intent(context, MostPopularDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }*/
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}