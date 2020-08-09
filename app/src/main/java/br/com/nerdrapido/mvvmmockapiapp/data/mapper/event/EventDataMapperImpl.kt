package br.com.nerdrapido.mvvmmockapiapp.data.mapper.event

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.person.PersonDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.cupom.CupomDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.EventResponse

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class EventDataMapperImpl(
    private val cupomDataMapper: CupomDataMapper,
    private val personDataMapper: PersonDataMapper
) : EventDataMapper {
    override fun mapRemoteToData(response: EventResponse): EventData {
        return EventData(
            response.date,
            response.description,
            response.image,
            response.longitude,
            response.latitude,
            response.price,
            response.title,
            response.id,
            cupomDataMapper.mapRemoteToDataList(response.cupons),
            personDataMapper.mapRemoteToDataList(response.people)
        )
    }

}