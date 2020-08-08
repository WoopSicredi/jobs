package br.com.nerdrapido.mvvmmockapiapp.remote.service

import br.com.nerdrapido.mvvmmockapiapp.remote.model.EventResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
interface EventService {

    /**
     * Endpoint de listagem de eventos.
     */
    @GET("events/")
    suspend fun getEventList(): List<EventResponse>

    /**
     * Endpoint de obtenção de item.
     */
    @GET("events/{id}")
    suspend fun getEvent(@Path("id") id: String): EventResponse
}