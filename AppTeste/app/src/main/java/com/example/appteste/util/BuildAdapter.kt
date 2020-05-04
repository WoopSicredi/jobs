package com.example.appteste.util

import androidx.recyclerview.widget.RecyclerView

fun <T: RecyclerView.ViewHolder> buildAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<T>,
    decorator: RecyclerView.ItemDecoration?,
    layoutManager: RecyclerView.LayoutManager,
    viewPool: RecyclerView.RecycledViewPool) {

    recyclerView.apply {
        this.layoutManager = layoutManager
        this.isNestedScrollingEnabled = false
        swapAdapter(adapter, false)
        setRecycledViewPool(viewPool)
        setHasFixedSize(true)

        if (decorator != null && itemDecorationCount == 0) {
            addItemDecoration(decorator)
        }
    }
}