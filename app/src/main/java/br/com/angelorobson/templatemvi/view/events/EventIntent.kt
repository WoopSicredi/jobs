package br.com.angelorobson.templatemvi.view.events

import br.com.angelorobson.templatemvi.model.domains.Event

sealed class EventIntent

object InitialIntent : EventIntent()

data class EventLoadedIntent(val events: List<Event>) : EventIntent()

data class GoToEventDetailIntent(val event: Event) : EventIntent()

data class EventExceptionIntent(val errorMessage: String) : EventIntent()
