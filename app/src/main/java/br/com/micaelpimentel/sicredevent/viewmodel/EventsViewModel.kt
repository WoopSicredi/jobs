package br.com.micaelpimentel.sicredevent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.micaelpimentel.sicredevent.api.model.*
import br.com.micaelpimentel.sicredevent.api.service.CheckinService
import br.com.micaelpimentel.sicredevent.api.service.EventService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NumberFormatException

class EventsViewModel(
    private val eventService: EventService,
    private val checkinService: CheckinService
) : ViewModel() {

    private val _eventsResponse = MutableLiveData<List<Event>>()
    val eventsResponse: LiveData<List<Event>>
        get() = _eventsResponse

    private val _getEventsError = MutableLiveData<ErrorResponse>()
    val getEventsError: LiveData<ErrorResponse>
        get() = _getEventsError

    fun getEvents() {
        eventService.getEvents().enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                if (response.isSuccessful) {
                    response.body()?.let { eventList ->
                        _eventsResponse.value = eventList
                    }
                } else {
                    _getEventsError.value = ErrorResponse("${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                _getEventsError.value = ErrorResponse("Erro ao acessar API - ${t.message}")
            }
        })
    }

    private val _eventDetailsResponse = MutableLiveData<Event>()
    val eventDetailsResponse: LiveData<Event>
        get() = _eventDetailsResponse

    private val _getEventDetailsError = MutableLiveData<ErrorResponse>()
    val getEventDetailsError: LiveData<ErrorResponse>
        get() = _getEventDetailsError

    fun getEventDetails(eventId: String) {
        eventService.getEventDetails(eventId).enqueue(object : Callback<Event> {
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                if (response.isSuccessful) {
                    response.body()?.let { event ->
                        _eventDetailsResponse.value = event
                    }
                } else {
                    _getEventDetailsError.value = ErrorResponse("${response.code()}")
                }
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                _getEventDetailsError.value = ErrorResponse("${t.message}")
            }
        })
    }

    private val _checkinResponse = MutableLiveData<String>()
    val checkinResponse: LiveData<String>
        get() = _checkinResponse

    private val _checkinError = MutableLiveData<Boolean>()
    val checkinError: LiveData<Boolean>
        get() = _checkinError

    fun requestEventCheckin(checkin: Checkin) {
        checkinService.requestCheckin(checkin).enqueue(object : Callback<CheckinResponse> {
            override fun onResponse(
                call: Call<CheckinResponse>,
                response: Response<CheckinResponse>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        try {
                            if (it.code.toInt() in 200..299) {
                                _checkinResponse.value = it.code
                            } else
                                _checkinError.value = true
                        } catch (e: NumberFormatException) {
                            _checkinError.value = true
                        }
                    }
                } else {
                    _checkinError.value = true
                }
            }

            override fun onFailure(call: Call<CheckinResponse>, t: Throwable) {
                _checkinError.value = true
            }

        })
    }
}