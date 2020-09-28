package br.com.angelorobson.templatemvi.view.eventdetail

import android.app.Dialog
import android.widget.EditText
import br.com.angelorobson.templatemvi.model.builders.EventBuilder
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Before
import org.junit.Test

class EventDetailViewModelTest {

    private lateinit var updateSpec: UpdateSpec<EventDetailModel, EventDetailIntent, EventDetailEffect>

    var emailEt: EditText? = null

    var nameEt: EditText? = null

    var circularButton: CircularProgressButton? = null

    var dialog: Dialog? = null

    @Before
    fun setUp() {
        updateSpec = UpdateSpec(::eventDetailUpdate)
    }

    @Test
    fun `when call initial event then ObserveEventEffect is dispatched`() {
        val event = EventBuilder.Builder().oneEvent().build()
        val model = EventDetailModel()
        updateSpec
                .given(model)
                .whenEvent(InitialIntent(event = event))
                .then(
                        assertThatNext<EventDetailModel, EventDetailEffect>(
                                hasNoModel(),
                                hasEffects(ObserveEventEffect(event = event))
                        )
                )
    }

    @Test
    fun `when call EventDetailLoadedIntent event then EventDetailLoadedIntent is updated`() {
        val event = EventBuilder.Builder().oneEvent().build()
        val model = EventDetailModel()

        updateSpec
                .given(model)
                .whenEvent(EventDetailLoadedIntent(event = event))
                .then(
                        assertThatNext<EventDetailModel, EventDetailEffect>(
                                hasModel(
                                        model.copy(eventDetailResult = EventDetailResult.EventDetailLoaded(event = event))
                                ),
                                hasNoEffects()
                        )
                )
    }

    @Test
    fun `when call EventDetailLoadedEventExceptionIntent event then EventDetailResult Error is updated`() {
        val messageError = "message error"
        val model = EventDetailModel()

        updateSpec
                .given(model)
                .whenEvent(EventDetailLoadedEventExceptionIntent(messageError))
                .then(
                        assertThatNext<EventDetailModel, EventDetailEffect>(
                                hasModel(
                                        model.copy(eventDetailResult = EventDetailResult.Error(messageError))
                                ),
                                hasNoEffects()
                        )
                )
    }


    @Test
    fun `when call ShareEventClickedIntent event then ClickedShareEventEffect is dispatched`() {
        val event = EventBuilder.Builder().oneEvent().build()
        val model = EventDetailModel()

        updateSpec
                .given(model)
                .whenEvent(ShareEventClickedIntent(event))
                .then(
                        assertThatNext<EventDetailModel, EventDetailEffect>(
                                hasNoModel(),
                                hasEffects(ClickedShareEventEffect(event))
                        )
                )
    }

    @Test
    fun `when call CheckInEventClickedIntent event then CheckInEventEffect is dispatched`() {
        val event = EventBuilder.Builder().oneEvent().build()

        val model = EventDetailModel()

        updateSpec
                .given(model)
                .whenEvent(CheckInEventClickedIntent(
                        event = event,
                        emailEt = emailEt,
                        nameEt = nameEt,
                        confirmButton = circularButton,
                        dialog = dialog
                ))
                .then(
                        assertThatNext<EventDetailModel, EventDetailEffect>(
                                hasNoModel(),
                                hasEffects(CheckInEventEffect(
                                        event = event,
                                        emailEt = emailEt,
                                        nameEt = nameEt,
                                        confirmButton = circularButton,
                                        dialog = dialog
                                ))
                        )
                )
    }

}