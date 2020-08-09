package br.com.nerdrapido.mvvmmockapiapp.data.mapper.person

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.base.DataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.PersonData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.PersonResponse

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
interface PersonDataMapper :
    DataMapper<PersonData, PersonResponse> {
}