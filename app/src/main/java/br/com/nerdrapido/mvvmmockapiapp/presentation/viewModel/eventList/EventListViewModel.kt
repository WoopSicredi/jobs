package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.eventList

import androidx.lifecycle.*
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
import br.com.nerdrapido.mvvmmockapiapp.presentation.exception.ApiCallNetworkException
import br.com.nerdrapido.mvvmmockapiapp.presentation.exception.NotMappedException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventListViewModel(private val getEventListUseCase: GetEventListUseCase) : ViewModel(),
    LifecycleObserver {

    val eventListStateLiveData = MutableLiveData<EventListViewState>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun load() {
        fetchEventList()
    }

    fun fetchEventList() {
        eventListStateLiveData.postValue(EventListViewState())
        GlobalScope.launch {
            // Nunca vai cair no "else"
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
                            ApiCallNetworkException(eventList.error)
                        )
                    )
                }
                else -> {
                    // Se cair aqui com certeza será DataWrapper.GenericError
                    val eventListCasted = eventList as DataWrapper.GenericError
                    eventListStateLiveData.postValue(
                        EventListViewState(
                            ViewStateEnum.FAILED,
                            NotMappedException(eventListCasted.error)
                        )
                    )
                }
            }
        }
    }
}
