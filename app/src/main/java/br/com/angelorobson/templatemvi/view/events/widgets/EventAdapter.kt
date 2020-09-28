package br.com.angelorobson.templatemvi.view.events.widgets

import br.com.angelorobson.templatemvi.R
import br.com.angelorobson.templatemvi.databinding.EventItemBinding
import br.com.angelorobson.templatemvi.model.domains.Event
import br.com.angelorobson.templatemvi.view.utils.BindingAdapter

class EventAdapter(private val onClick: (Event) -> Unit) : BindingAdapter<Event, EventItemBinding>() {

    override fun getLayoutResId(): Int = R.layout.event_item

    override fun onBindViewHolder(binding: EventItemBinding, position: Int) {
        binding.run {
            val event = getItem(position)
            item = event
            eventItemConstraintLayout.setOnClickListener { onClick.invoke(event) }
            executePendingBindings()
        }
    }
}