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
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.model.IslandDetails

class AdapterIslandListHome(islandList: MutableList<IslandDetails>) :
    BaseAdapter<IslandDetails>(islandList) {

    private lateinit var islandListener: IslandListener

    fun setListener(islandListener: IslandListener) {
        this.islandListener = islandListener
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return IslandViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_home_island_list_item, parent, false)
        )
    }

    override fun onBindData(holderBase: RecyclerView.ViewHolder, baseValue: IslandDetails) {
        val holder = holderBase as IslandViewHolder
        holder.textViewIslandName.text = baseValue.title
        holder.textViewIslandDescription.text = baseValue.sub_title
        Glide.with(context).load(URLHelper.ISLAND_IMAGE_URL + baseValue.island_image)
            .placeholder(R.drawable.home01)
            .into(holder.imageViewIslandImage)
    }

    inner class IslandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewIslandImage: ImageView = itemView.findViewById(R.id.imageViewIslandImage)
        val textViewIslandName: TextView = itemView.findViewById(R.id.textViewIslandName)
        val textViewIslandDescription: TextView =
            itemView.findViewById(R.id.textViewIslandDescription)

        init {
            itemView.setOnClickListener {
                islandListener.onView(getItem(adapterPosition))
            }
        }
    }

    interface IslandListener {
        fun onView(islandDetails: IslandDetails)
    }

    /*val intent = Intent(context, HomeCategoryActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)*/
}
