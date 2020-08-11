package br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.checkIn

import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.CheckIn

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class CheckInMapperImpl : CheckInMapper {

    override fun mapModelToData(model: CheckIn): CheckInData {
        return CheckInData(
            model.eventId,
            model.name,
            model.email
        )
    }

    override fun mapDataToModel(data: CheckInData): CheckIn {
        return CheckIn(
            data.eventId,
            data.name,
            data.email
        )
    }
}