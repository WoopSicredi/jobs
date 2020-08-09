package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.eventList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventListViewModelImpl(private val getEventListUseCase: GetEventListUseCase) : ViewModel(),
    EventListViewModel {

    override val eventListStateLiveData = MutableLiveData<EventListViewState>()

    override fun fetchEventList() {
        eventListStateLiveData.postValue(EventListViewState())
        GlobalScope.launch {
            when (val eventList = getEventListUseCase.execute(GetEventListUseCaseInput())) {
                is DataWrapper.Success<List<EventData>> -> {
                    eventListStateLiveData.postValue(
                        EventListViewState(
                            ViewStateEnum.SUCCESS,
                            null,
                            eventList.value
                        )
                    )
                }
                is DataWrapper.NetworkError -> {
                    eventListStateLiveData.postValue(
                        EventListViewState(
                            ViewStateEnum.FAILED,
                            eventList.error,
                            emptyList()
                        )
                    )
                }
                else -> {
                    throw RuntimeException("Unknown Error")
                }
            }
        }
    }
}
