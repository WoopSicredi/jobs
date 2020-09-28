package br.com.angelorobson.templatemvi.view.eventdetail

import br.com.angelorobson.templatemvi.model.domains.Event

data class EventDetailModel(
        val eventDetailResult: EventDetailResult = EventDetailResult.EventDetailLoaded()
)

sealed class EventDetailResult {
    data class Error(
            val errorMessage: String,
            val isLoading: Boolean = false
    ) : EventDetailResult()

    data class EventDetailLoaded(
            val event: Event? = null,
            val isLoading: Boolean = true,
            val hasError: Boolean = false
    ) : EventDetailResult()
}