package br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.event

import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.cupom.CupomModelMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.person.PersonModelMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Event
import java.text.DateFormat
import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class EventModelMapperImpl(
    private val cupomModelMapper: CupomModelMapper,
    private val personModelMapper: PersonModelMapper
) : EventModelMapper {
    override fun mapDataToModel(data: EventData): Event {
        return Event(
            mapLongToDateString(data.date),
            data.description,
            data.image,
            data.longitude,
            data.latitude,
            data.price,
            data.title,
            data.id,
            cupomModelMapper.mapDataToModelList(data.cupons),
            personModelMapper.mapDataToModelList(data.people)
        )
    }

    private fun mapLongToDateString(dateLong: Long): String {
        val date = DateFormat.getDateTimeInstance()
        return date.format(Date(dateLong))
    }
}
