package com.mns.banzosapp.adapters.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollRecyclerViewListener : RecyclerView.OnScrollListener() {
    private var isLoading = true
    private var isLoadingCompleted = false
    private var linearLayoutManager: LinearLayoutManager? = null
    override fun onScrolled(mRecyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(mRecyclerView, dx, dy)
        if (linearLayoutManager == null) linearLayoutManager = mRecyclerView
            .layoutManager as LinearLayoutManager?
        val totalItemCount = linearLayoutManager!!.itemCount
        val lastVisibleItem = linearLayoutManager!!.findLastVisibleItemPosition()
        val visibleThreshold = 2
        if (totalItemCount > 9) if (!isLoadingCompleted && !isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
            onLoadMore()
            isLoading = true
        }
    }

    fun setLoadedStatus(loading: Boolean) {
        isLoading = loading
    }

    abstract fun onLoadMore()
    fun setLoadingCompleted(loadingCompleted: Boolean) {
        isLoadingCompleted = loadingCompleted
    }
}