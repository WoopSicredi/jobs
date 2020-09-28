package br.com.angelorobson.templatemvi.view.eventdetail

import android.content.Intent
import android.widget.Toast
import br.com.angelorobson.templatemvi.R
import br.com.angelorobson.templatemvi.model.domains.CheckIn
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

fun eventDetailUpdate(
        model: EventDetailModel,
        event: EventDetailIntent
): Next<EventDetailModel, EventDetailEffect> {
    return when (event) {
        is InitialIntent -> dispatch(setOf(ObserveEventEffect(event = event.event)))
        is EventDetailLoadedIntent -> next(model.copy(eventDetailResult = EventDetailResult.EventDetailLoaded(event.event)))
        is EventDetailLoadedEventExceptionIntent -> next(model.copy(eventDetailResult = EventDetailResult.Error(event.errorMessage)))
        is ShareEventClickedIntent -> dispatch(setOf(ClickedShareEventEffect(event = event.event)))
        is CheckInEventClickedIntent -> dispatch(setOf(CheckInEventEffect(
                event = event.event,
                dialog = event.dialog,
                confirmButton = event.confirmButton,
                nameEt = event.nameEt,
                emailEt = event.emailEt
        )))
    }
}

class EventDetailViewModel @Inject constructor(
        repository: EventRepository,
        activityService: ActivityService,
        navigator: Navigator,
        idlingResource: IdlingResource
) : MobiusVM<EventDetailModel, EventDetailIntent, EventDetailEffect>(
        "EventDetailViewModel",
        Update(::eventDetailUpdate),
        EventDetailModel(),
        RxMobius.subtypeEffectHandler<EventDetailEffect, EventDetailIntent>()
                .addTransformer(ObserveEventEffect::class.java) { upstream ->
                    idlingResource.increment()
                    upstream.switchMap {
                        repository.getEvent(it.event.id)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .map { event ->
                                    idlingResource.decrement()
                                    EventDetailLoadedIntent(event) as EventDetailIntent
                                }
                                .onErrorReturn {
                                    val errorMessage = HandlerErrorRemoteDataSource.validateStatusCode(it)
                                    activityService.activity.toast(errorMessage, Toast.LENGTH_LONG)
                                    EventDetailLoadedEventExceptionIntent(errorMessage)
                                }
                    }
                }
                .addConsumer(ClickedShareEventEffect::class.java) { consumer ->
                    val event = consumer.event
                    var shareText = "${event.title}\n\n"
                    shareText += event.description

                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, shareText)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    activityService.activity.startActivity(shareIntent)
                }
                .addConsumer(CheckInEventEffect::class.java) { consumer ->
                    val email = consumer.emailEt
                    val name = consumer.nameEt
                    val event = consumer.event
                    val confirmButton = consumer.confirmButton
                    val dialog = consumer.dialog

                    if (name?.text?.isEmpty()!!) {
                        confirmButton?.revertAnimation()
                        name.error = activityService.activity.getString(R.string.insert_your_name)
                        return@addConsumer
                    }

                    if (email?.text?.isEmpty()!!) {
                        confirmButton?.revertAnimation()
                        email.error = activityService.activity.getString(R.string.insert_your_email)
                        return@addConsumer
                    }

                    val checkIn = CheckIn(
                            eventId = event.id,
                            email = email.text.toString(),
                            name = name.text.toString()
                    )

                    repository.checkIn(checkIn)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    {
                                        confirmButton?.revertAnimation()
                                        dialog?.dismiss()
                                        activityService.activity.toast(activityService.activity.getString(R.string.check_in_success), Toast.LENGTH_LONG)
                                    },
                                    {
                                        confirmButton?.revertAnimation()
                                        val errorMessage = HandlerErrorRemoteDataSource.validateStatusCode(it)
                                        activityService.activity.toast(errorMessage, Toast.LENGTH_LONG)
                                        EventDetailLoadedEventExceptionIntent(errorMessage)
                                    }
                            )

                }
                .build()
)
