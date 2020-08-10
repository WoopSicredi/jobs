package br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.person

import br.com.nerdrapido.mvvmmockapiapp.data.model.PersonData
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Person

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class PersonModelMapperImpl : PersonModelMapper {
    override fun mapDataToModel(data: PersonData): Person {
        return Person(data.id, data.eventId, data.name, data.picture)
    }
}