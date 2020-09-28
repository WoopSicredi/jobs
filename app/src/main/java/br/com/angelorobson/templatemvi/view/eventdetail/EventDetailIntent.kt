package br.com.angelorobson.templatemvi.view.eventdetail

import android.app.Dialog
import android.widget.EditText
import br.com.angelorobson.templatemvi.model.domains.Event
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton

sealed class EventDetailIntent

data class InitialIntent(val event: Event) : EventDetailIntent()
data class EventDetailLoadedIntent(val event: Event) : EventDetailIntent()
data class ShareEventClickedIntent(val event: Event) : EventDetailIntent()
data class CheckInEventClickedIntent(val event: Event,
                                     val nameEt: EditText?,
                                     val emailEt: EditText?,
                                     val confirmButton: CircularProgressButton?,
                                     val dialog: Dialog?) : EventDetailIntent()

data class EventDetailLoadedEventExceptionIntent(val errorMessage: String) : EventDetailIntent()

