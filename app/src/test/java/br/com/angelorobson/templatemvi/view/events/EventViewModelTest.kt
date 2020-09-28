package br.com.angelorobson.templatemvi.view.events

import br.com.angelorobson.templatemvi.model.builders.EventBuilder
import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Before
import org.junit.Test

class EventViewModelTest {

    private lateinit var updateSpec: UpdateSpec<EventModel, EventIntent, EventEffect>

    @Before
    fun setUp() {
        updateSpec = UpdateSpec(::eventUpdate)
    }

    @Test
    fun `when call initial event then ObserveEventsEffect is dispatched`() {
        val model = EventModel()
        updateSpec
                .given(model)
                .whenEvent(InitialIntent)
                .then(
                        assertThatNext<EventModel, EventEffect>(
                                hasNoModel(),
                                hasEffects(ObserveEventsEffect)
                        )
                )
    }

    @Test
    fun `when call EventLoadedIntent event then EventsLoaded is updated`() {
        val event = EventBuilder.Builder().oneEvent().build()
        val events = listOf(event, event, event)

        val model = EventModel()

        updateSpec
                .given(model)
                .whenEvent(EventLoadedIntent(events = events))
                .then(
                        assertThatNext<EventModel, EventEffect>(
                                hasModel(
                                        model.copy(eventResult = EventResult.EventsLoaded(events = events))
                                ),
                                hasNoEffects()
                        )
                )
    }

    @Test
    fun `when call EventExceptionIntent event then EventResult is updated`() {
        val errorMessage = "error message"

        val model = EventModel()

        updateSpec
                .given(model)
                .whenEvent(EventExceptionIntent(errorMessage = errorMessage))
                .then(
                        assertThatNext<EventModel, EventEffect>(
                                hasModel(
                                        model.copy(eventResult = EventResult.Error(errorMessage = errorMessage))
                                ),
                                hasNoEffects()
                        )
                )
    }

    @Test
    fun `when call GoToEventDetailIntent event then EventClickedEffect is dispatched`() {
        val event = EventBuilder.Builder().oneEvent().build()

        val model = EventModel()

        updateSpec
                .given(model)
                .whenEvent(GoToEventDetailIntent(event = event))
                .then(
                        assertThatNext<EventModel, EventEffect>(
                                hasEffects(
                                        EventClickedEffect(event = event)
                                ),
                                hasNoModel()
                        )
                )
    }

}