package com.mns.banzosapp.adapters.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.model.base.MetaResponseDetails
import java.util.*

abstract class BaseAdapterLoadingAndSearch<T>(var items: MutableList<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var itemsFiltered: MutableList<T>
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
        if (itemsFiltered[position] != "0") onBindData(holder, itemsFiltered[position]!!)
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
        return itemsFiltered.size
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

    fun setPageNumber(pageNumber: String) {
        metaDetails?.current_page = pageNumber
    }

    fun addItems(savedCardItems: ArrayList<T>) {
        itemsFiltered = savedCardItems
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
        return itemsFiltered[position]
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == "0") VIEW_TYPE_LOADING else VIEW_TYPE_DATA
    }

    fun updateItem(position: Int, t: T) {
        itemsFiltered[position] = t
        this.notifyItemChanged(position)
    }

    val dataList: List<T?>
        get() = itemsFiltered

    fun removeLoadingIcon() {
        if (endlessScrollRecyclerViewListener == null && endlessScrollNestedScrollViewListener == null) return
        if (isLoadingAdded) {
            itemsFiltered.removeAt(itemsFiltered.size - 1)
            isLoadingAdded = false
        }
        notifyItemRemoved(itemCount)
        endlessScrollRecyclerViewListener?.setLoadedStatus(false)
        endlessScrollNestedScrollViewListener?.setLoadedStatus(false)
    }

    fun addLoadingIcon() {
        if (endlessScrollRecyclerViewListener == null && endlessScrollNestedScrollViewListener == null) return
        itemsFiltered.add("0" as T)
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                var filteredList: MutableList<T> = ArrayList()
                if (charString.isEmpty()) {
                    filteredList = items
                } else {
                    for (row in items) {
                        if (row.toString().toLowerCase(Locale.ROOT).contains(
                                charString.toLowerCase(
                                    Locale.ROOT
                                )
                            )
                        ) {
                            filteredList.add(row)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                itemsFiltered = filterResults.values as MutableList<T>
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_DATA = 1
    }

    init {
        this.itemsFiltered = items
    }
}
