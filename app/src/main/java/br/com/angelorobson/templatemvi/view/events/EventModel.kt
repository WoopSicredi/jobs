package br.com.angelorobson.templatemvi.view.events

import br.com.angelorobson.templatemvi.model.domains.Event

data class EventModel(
        val eventResult: EventResult = EventResult.EventsLoaded()
)

sealed class EventResult {
    data class Error(
            val errorMessage: String,
            val isLoading: Boolean = false
    ) : EventResult()

    data class EventsLoaded(
            val events: List<Event> = listOf(),
            val isLoading: Boolean = false,
            val hasError: Boolean = false
    ) : EventResult()
}