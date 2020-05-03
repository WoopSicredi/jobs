package com.example.appteste.view.event

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appteste.model.Event
import kotlinx.android.synthetic.main.item_event.view.*

class ItemEventViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(event: Event) {
        itemView.text_title.text = event.title
        Glide.with(itemView.context).load(event.image).into(itemView.image_event)
    }
}