package com.example.appteste.view.event

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteste.R
import com.example.appteste.model.Event
import com.example.appteste.view.eventdetail.EventDetailActivity

private const val ITEM_EVENT_LIST = 1

class EventAdapter(var eventList: ArrayList<Event> = arrayListOf()): RecyclerView.Adapter<ItemEventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemEventViewHolder {
        return ItemEventViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_event, parent, false)
        )
    }

    override fun getItemCount() = getElements().size

    override fun onBindViewHolder(holder: ItemEventViewHolder, position: Int) {
        val event = eventList[position]
        holder.bind(event)
        holder.itemView.setOnClickListener {
            holder.itemView.context.let {
                val eventDetailIntent = Intent(it, EventDetailActivity::class.java).apply {
                    putExtra(EventDetailActivity.EVENT_EXTRA, event)
                }
                it.startActivity(eventDetailIntent)

            }
        }
    }

    private fun getElements(): ArrayList<Int> {
        val elements = arrayListOf<Int>()
        if (eventList.isNotEmpty()) {
            repeat(eventList.size) {
                elements.add(ITEM_EVENT_LIST)
            }
        }
        return elements
    }

    fun addEvents(events: ArrayList<Event>) {
        eventList.clear()
        eventList = events
        notifyDataSetChanged()
    }



}