package br.com.micaelpimentel.sicredevent.api.service

import br.com.micaelpimentel.sicredevent.api.model.Checkin
import br.com.micaelpimentel.sicredevent.api.model.CheckinResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckinService {
    @POST("api/checkin")
    fun requestCheckin(@Body checkinRequest: Checkin): Call<CheckinResponse>
}
