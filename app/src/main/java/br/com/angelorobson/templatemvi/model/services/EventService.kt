package br.com.angelorobson.templatemvi.model.services

import br.com.angelorobson.templatemvi.model.dtos.request.CheckInRequestDto
import br.com.angelorobson.templatemvi.model.dtos.response.EventResponseDto
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {

    @GET("api/events")
    fun getAll(): Observable<List<EventResponseDto>>

    @GET("api/events/{id}")
    fun getEvent(@Path("id") id: String): Observable<EventResponseDto>

    @POST("api/checkin")
    fun checkIn(@Body checkInRequestDto: CheckInRequestDto): Completable
}