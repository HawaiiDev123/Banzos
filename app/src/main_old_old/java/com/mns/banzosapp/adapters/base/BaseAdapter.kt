package com.mns.banzosapp.adapters.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import java.util.*

abstract class BaseAdapter<T>(items: MutableList<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: MutableList<T>
    public lateinit var context: Context
    abstract fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun onBindData(holderBase: RecyclerView.ViewHolder, baseValue: T)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return if (viewType == VIEW_TYPE_LOADING) LoadingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.loading_items, parent, false)
        ) else setViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items[position] != null) onBindData(holder, items[position]!!)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(savedCardItemz: ArrayList<T>) {
        items = savedCardItemz
        notifyDataSetChanged()
    }

    private inner class LoadingViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            Glide.with(context).load(R.drawable.loading_banzos).into(imageView)
        }
    }

    fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null) VIEW_TYPE_LOADING else VIEW_TYPE_DATA
    }

    fun updateItem(position: Int, t: T) {
        items[position] = t
        this.notifyItemChanged(position)
    }

    val dataList: List<T?>
        get() = items

    fun removeLoadingIcon() {
        items.removeAt(items.size - 1)
        notifyItemRemoved(itemCount)
    }

    fun addLoadingIcon() {
//        items.add()
//        notifyItemInserted(itemCount)
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_DATA = 1
    }

    init {
        this.items = items
    }
}
