package br.com.micaelpimentel.sicredevent.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.micaelpimentel.sicredevent.api.model.Event
import br.com.micaelpimentel.sicredevent.databinding.ListItemBinding
import br.com.micaelpimentel.sicredevent.extensions.formatCurrencyToBr
import br.com.micaelpimentel.sicredevent.extensions.setupEventImage

class EventListAdapter(
    private val eventList: List<Event>,
    var onItemClickListener: (eventId: String) -> Unit = {}
): RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    inner class ViewHolder(val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {

        private lateinit var selectedEvent: Event

        init {
            itemView.setOnClickListener {
                onItemClickListener(selectedEvent.id)
            }
        }

        fun bind(event: Event) {
            this.selectedEvent = event
            with(listItemBinding) {
                eventNameTextView.text = event.title
                eventDescriptionTextView.text = event.description
                eventPriceTextView.text = event.price.formatCurrencyToBr()
                eventImageView.setupEventImage(event.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let { ViewHolder(it) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(eventList[position])

    override fun getItemCount() = eventList.size
}