package br.com.angelorobson.templatemvi.view.events

import android.widget.Toast
import br.com.angelorobson.templatemvi.model.repositories.EventRepository
import br.com.angelorobson.templatemvi.view.utils.*
import com.spotify.mobius.Next
import com.spotify.mobius.Next.dispatch
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

fun eventUpdate(
        model: EventModel,
        event: EventIntent
): Next<EventModel, EventEffect> {
    return when (event) {
        is InitialIntent -> dispatch(setOf(ObserveEventsEffect))
        is EventLoadedIntent -> next(model.copy(eventResult = EventResult.EventsLoaded(event.events)))
        is EventExceptionIntent -> next(model.copy(eventResult = EventResult.Error(event.errorMessage)))
        is GoToEventDetailIntent -> dispatch(setOf(EventClickedEffect(event.event)))
    }
}

class EventViewModel @Inject constructor(
        repository: EventRepository,
        activityService: ActivityService,
        navigator: Navigator,
        idlingResource: IdlingResource
) : MobiusVM<EventModel, EventIntent, EventEffect>(
        "EventViewModel",
        Update(::eventUpdate),
        EventModel(),
        RxMobius.subtypeEffectHandler<EventEffect, EventIntent>()
                .addTransformer(ObserveEventsEffect::class.java) { upstream ->
                    idlingResource.increment()
                    upstream.switchMap {
                        repository.getAll()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .map {
                                    idlingResource.decrement()
                                    EventLoadedIntent(it) as EventIntent
                                }
                                .onErrorReturn {
                                    val errorMessage = HandlerErrorRemoteDataSource.validateStatusCode(it)
                                    activityService.activity.toast(errorMessage, Toast.LENGTH_LONG)
                                    EventExceptionIntent(errorMessage)
                                }
                    }
                }
                .addConsumer(EventClickedEffect::class.java) { consumer ->
                    val event = consumer.event
                    navigator.to(EventFragmentDirections.toEventDetailFragment(event))
                }
                .build()
)