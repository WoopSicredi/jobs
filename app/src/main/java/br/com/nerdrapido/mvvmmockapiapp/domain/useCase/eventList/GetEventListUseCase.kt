package br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList

import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.base.BaseUseCase

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
interface GetEventListUseCase : BaseUseCase<GetEventListUseCaseInput, DataWrapper<List<EventData>>> {
}