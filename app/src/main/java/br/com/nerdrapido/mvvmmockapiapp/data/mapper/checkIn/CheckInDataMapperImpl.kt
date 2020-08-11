package br.com.nerdrapido.mvvmmockapiapp.data.mapper.checkIn

import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.CheckInPost

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class CheckInDataMapperImpl : CheckInDataMapper {


    override fun mapDataToRemote(data: CheckInData): CheckInPost {
        return CheckInPost(
            data.eventId,
            data.name,
            data.email
        )
    }

    override fun mapRemoteToData(response: CheckInPost): CheckInData {
        return CheckInData(
            response.eventId,
            response.name,
            response.email
        )
    }
}