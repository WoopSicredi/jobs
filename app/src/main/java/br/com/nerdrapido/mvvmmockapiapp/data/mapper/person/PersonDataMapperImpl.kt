package br.com.nerdrapido.mvvmmockapiapp.data.mapper.person

import br.com.nerdrapido.mvvmmockapiapp.data.model.PersonData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.PersonResponse

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class PersonDataMapperImpl :
    PersonDataMapper {

    override fun mapRemoteToData(response: PersonResponse): PersonData {
        return PersonData(response.id, response.eventId, response.name, response.picture)
    }
}