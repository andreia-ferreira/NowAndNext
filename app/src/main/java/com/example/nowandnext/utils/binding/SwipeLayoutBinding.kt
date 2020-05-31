package com.example.nowandnext.utils.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object SwipeLayoutBinding {

    @JvmStatic
    @BindingAdapter("swipeListener")
    fun setSwipeListener(swipeLayout: SwipeRefreshLayout, swipeListener: SwipeRefreshLayout.OnRefreshListener?) {
        if (swipeListener != null) {
            swipeLayout.setOnRefreshListener(swipeListener)
        }
    }

    @JvmStatic
    @BindingAdapter("isRefreshing")
    fun setIsRefreshing(swipeLayout: SwipeRefreshLayout, state: Boolean) {
        swipeLayout.isRefreshing = state
    }

}