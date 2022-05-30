package br.com.micaelpimentel.sicredevent.api.service

import br.com.micaelpimentel.sicredevent.api.model.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {
    @GET("api/events")
    fun getEvents(): Call<List<Event>>

    @GET("api/events/{eventId}")
    fun getEventDetails(@Path("eventId") eventId: String): Call<Event>
}