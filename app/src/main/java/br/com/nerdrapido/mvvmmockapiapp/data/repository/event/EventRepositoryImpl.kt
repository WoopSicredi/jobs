package br.com.nerdrapido.mvvmmockapiapp.data.repository.event

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.event.EventDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.remote.network.NetworkController
import br.com.nerdrapido.mvvmmockapiapp.remote.service.EventService

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class EventRepositoryImpl(networkController: NetworkController, private val eventDataMapper: EventDataMapper) :
    EventRepository {

    private val service = networkController.retrofit.create(EventService::class.java)

    override suspend fun getEventList(): DataWrapper<List<EventData>> {
        return safeApiCall {
            val eventListRemote = service.getEventList()
            return@safeApiCall eventDataMapper.mapRemoteToDataList(eventListRemote)
        }
    }
}