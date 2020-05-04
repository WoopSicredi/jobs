package com.example.appteste.network

import com.example.appteste.extensions.launchSafe
import com.example.appteste.model.CheckIn
import com.example.appteste.model.Event
import com.example.appteste.util.LiveDataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class MainNetwork(private val dispatcher: CoroutineDispatcher){
    private val TAG_LIST = "EventList"
    private val TAG_CHECKIN = "CheckIn"

    private val api = Controller.retrofit.create(EventApi::class.java)
    fun getEventList(listener: (LiveDataResult<ArrayList<Event>>) -> Unit) {
        CoroutineScope(dispatcher).launchSafe(
            {
                listener(LiveDataResult.error(it))
            },
            {
                val request = api.getEventList()
                listener(DefaultNetwork.request(request, TAG_LIST))
            }
        )
    }
    fun postCheckIn(checkIn: CheckIn, listener: (LiveDataResult<Void>) -> Unit) {
        CoroutineScope(dispatcher).launchSafe(
            {
                listener(LiveDataResult.error(it))
            },
            {
                val request = api.postCheckIn(checkIn)
                listener(DefaultNetwork.request(request, TAG_CHECKIN))
            }
        )
    }
}

interface EventApi {
    @GET("events")
    suspend fun getEventList(): Response<ArrayList<Event>>
    @POST("checkin")
    suspend fun postCheckIn(@Body checkIn: CheckIn): Response<Void>
}