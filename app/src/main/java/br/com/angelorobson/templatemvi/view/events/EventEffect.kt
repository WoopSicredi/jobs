package br.com.angelorobson.templatemvi.view.events

import br.com.angelorobson.templatemvi.model.domains.Event

sealed class EventEffect

object ObserveEventsEffect : EventEffect()
data class EventClickedEffect(val event: Event) : EventEffect()