package br.com.nerdrapido.mvvmmockapiapp.data.mapper

import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.EventResponse

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class EventDataMapperImpl(
    private val cupomDataMapper: CupomDataMapper,
    private val personDataMapper: PersonDataMapper
) : EventDataMaper{
    override fun mapRemoteToData(response: EventResponse): EventData {
        return EventData(
            personDataMapper.mapRemoteToDataList(response.people),
            response.date,
            response.description,
            response.image,
            response.longitude,
            response.latitude,
            response.price,
            response.title,
            response.id,
            cupomDataMapper.mapRemoteToDataList(response.cupons)
        )
    }

}