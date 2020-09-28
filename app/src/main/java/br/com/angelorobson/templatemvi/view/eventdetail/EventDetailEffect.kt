package br.com.angelorobson.templatemvi.view.eventdetail

import android.app.Dialog
import android.widget.EditText
import br.com.angelorobson.templatemvi.model.domains.Event
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton

sealed class EventDetailEffect

data class ObserveEventEffect(val event: Event) : EventDetailEffect()
data class ClickedShareEventEffect(val event: Event) : EventDetailEffect()
data class CheckInEventEffect(val event: Event,
                              val nameEt: EditText?,
                              val emailEt: EditText?,
                              val confirmButton: CircularProgressButton?,
                              val dialog: Dialog?) : EventDetailEffect()