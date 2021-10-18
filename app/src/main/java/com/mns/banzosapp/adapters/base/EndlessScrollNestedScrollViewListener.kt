package com.mns.banzosapp.adapters.base

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollNestedScrollViewListener protected constructor(layoutManager: RecyclerView.LayoutManager) :
    NestedScrollView.OnScrollChangeListener {
    private var loading = true
    private var isLoadingCompleted = false
    private val mLayoutManager: LinearLayoutManager
    override fun onScrollChange(scrollView: NestedScrollView, x: Int, y: Int, dx: Int, dy: Int) {
        val totalItemCount = mLayoutManager.itemCount
        val lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
        val visibleThreshold = 2
        if (totalItemCount > 9) if (!isLoadingCompleted && !loading && totalItemCount <= lastVisibleItem + visibleThreshold) {
            onLoadMore()
            loading = true
        }
    }

    fun setLoadedStatus(loading: Boolean) {
        this.loading = loading
    }

    abstract fun onLoadMore()
    fun setLoadingCompleted(loadingCompleted: Boolean) {
        isLoadingCompleted = loadingCompleted
    }

    init {
        mLayoutManager = layoutManager as LinearLayoutManager
    }
}