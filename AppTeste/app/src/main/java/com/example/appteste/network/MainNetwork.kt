package com.example.appteste.network

import com.example.appteste.extensions.launchSafe
import com.example.appteste.model.Event
import com.example.appteste.util.LiveDataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import retrofit2.http.GET

class MainNetwork(private val dispatcher: CoroutineDispatcher){
    private val TAG = "EventList"

    private val api = Controller.retrofit.create(EventApi::class.java)
    fun getEventList(listener: (LiveDataResult<ArrayList<Event>>) -> Unit) {
        CoroutineScope(dispatcher).launchSafe(
            {
                listener(LiveDataResult.error(it))
            },
            {
                val request = api.getEventList()
                listener(DefaultNetwork.request(request, TAG))
            }
        )
    }
}

interface EventApi {
    @GET("events")
    suspend fun getEventList(): Response<ArrayList<Event>>
}