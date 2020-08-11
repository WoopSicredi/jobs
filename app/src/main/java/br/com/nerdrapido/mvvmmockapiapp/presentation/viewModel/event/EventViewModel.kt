package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.event.EventModelMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Event
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventViewModel(
    private val getEventListUseCase: GetEventListUseCase,
    private val eventModelMapper: EventModelMapper
) : ViewModel() {

    private val eventList: MutableLiveData<List<Event>> by lazy {
        MutableLiveData<List<Event>>().also {
            fetchEventList()
        }
    }

    private val viewState = MutableLiveData<ViewStateEnum>(ViewStateEnum.LOADING)

    fun getEventList(): LiveData<List<Event>> {
        return eventList
    }

    fun getViewState(): LiveData<ViewStateEnum> {
        return viewState
    }

    fun onTryAgainClick() {
        fetchEventList()
    }

    private fun fetchEventList() {
        Timber.d("fetchEventList")
        viewState.postValue(ViewStateEnum.LOADING)
        GlobalScope.launch {
            // Nunca vai cair no "else"
            when (val eventDataList = getEventListUseCase.execute(GetEventListUseCaseInput())) {
                is DataWrapper.Success<List<EventData>> -> {
                    viewState.postValue(ViewStateEnum.SUCCESS)
                    eventList.postValue(eventModelMapper.mapDataToModelList(eventDataList.value))
                }
                else -> viewState.postValue(ViewStateEnum.FAILED)
            }
        }
    }
}
