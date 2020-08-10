package br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList

import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.data.repository.event.EventRepository

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
open class GetEventListUseCaseImpl(private val eventRepository: EventRepository) : GetEventListUseCase {

    override suspend fun execute(input: GetEventListUseCaseInput): DataWrapper<List<EventData>> {
        return eventRepository.getEventList()
    }
}
