package com.mns.banzosapp.adapters.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.model.base.MetaResponseDetails
import java.util.*

abstract class BaseAdapterLoading<T>(items: MutableList<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: MutableList<T>
    lateinit var context: Context
    private var metaDetails: MetaResponseDetails? = null
    private var isLoadingAdded = false
    private var endlessScrollRecyclerViewListener: EndlessScrollRecyclerViewListener? = null
    private var endlessScrollNestedScrollViewListener: EndlessScrollNestedScrollViewListener? = null
    abstract fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun onBindData(holderBase: RecyclerView.ViewHolder, baseValue: T)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return if (viewType == VIEW_TYPE_LOADING) LoadingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.loading_items, parent, false)
        ) else setViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items[position] != "0") onBindData(holder, items[position]!!)
    }

    open fun setEndlessScrollRecyclerViewListener(
        recyclerView: RecyclerView,
        endlessScrollRecyclerViewListener: EndlessScrollRecyclerViewListener
    ) {
        this.endlessScrollRecyclerViewListener = endlessScrollRecyclerViewListener
        recyclerView.addOnScrollListener(endlessScrollRecyclerViewListener)
    }

    open fun setEndlessNestedScrollViewListener(
        nestedScrollView: NestedScrollView,
        endlessScrollNestedScrollViewListener: EndlessScrollNestedScrollViewListener?
    ) {
        this.endlessScrollNestedScrollViewListener = endlessScrollNestedScrollViewListener
        nestedScrollView.setOnScrollChangeListener(endlessScrollNestedScrollViewListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setMetaDetails(metaDetails: MetaResponseDetails?) {
        this.metaDetails = metaDetails
    }

    fun getMetaDetails(): MetaResponseDetails? {
        return metaDetails
    }

    fun getPageNumber(): String {
        return metaDetails?.current_page ?: "1"
    }

    fun addItems(savedCardItems: ArrayList<T>) {
        items = savedCardItems
        notifyDataSetChanged()
    }

    private inner class LoadingViewHolder(itemView: View) :
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
        return if (getItem(position) == "0") VIEW_TYPE_LOADING else VIEW_TYPE_DATA
    }

    fun updateItem(position: Int, t: T) {
        items[position] = t
        this.notifyItemChanged(position)
    }

    val dataList: List<T?>
        get() = items

    fun removeLoadingIcon() {
        if (endlessScrollRecyclerViewListener == null && endlessScrollNestedScrollViewListener == null) return
        if (isLoadingAdded) {
            items.removeAt(items.size - 1)
            isLoadingAdded = false
        }
        notifyItemRemoved(itemCount)
        endlessScrollRecyclerViewListener?.setLoadedStatus(false)
        endlessScrollNestedScrollViewListener?.setLoadedStatus(false)
    }

    fun addLoadingIcon() {
        if (endlessScrollRecyclerViewListener == null && endlessScrollNestedScrollViewListener == null) return
        items.add("0" as T)
        if (!isLoadingAdded) {
            notifyItemInserted(itemCount)
            isLoadingAdded = true
        }
        endlessScrollRecyclerViewListener?.setLoadedStatus(true)
        endlessScrollNestedScrollViewListener?.setLoadedStatus(true)
    }

    open fun setLoadingCompleted(loadingCompleted: Boolean) {
        if (endlessScrollRecyclerViewListener == null && endlessScrollNestedScrollViewListener == null) return
        endlessScrollRecyclerViewListener?.setLoadingCompleted(
            loadingCompleted
        )
        endlessScrollNestedScrollViewListener?.setLoadingCompleted(
            loadingCompleted
        )
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_DATA = 1
    }

    init {
        this.items = items
    }
}
