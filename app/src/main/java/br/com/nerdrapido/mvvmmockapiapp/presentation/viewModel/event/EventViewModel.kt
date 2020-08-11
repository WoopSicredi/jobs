package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn.PostEventCheckInUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn.PostEventCheckInUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.checkIn.CheckInMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.event.EventModelMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.CheckIn
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Event
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class EventViewModel(
    private val getEventListUseCase: GetEventListUseCase,
    private val postEventCheckInUseCase: PostEventCheckInUseCase,
    private val eventModelMapper: EventModelMapper,
    private val checkInMapper: CheckInMapper
) : ViewModel() {

    private val eventList: MutableLiveData<List<Event>> by lazy {
        MutableLiveData<List<Event>>().also {
            fetchEventList()
        }
    }

    private val eventSelected = MutableLiveData<Event>()

    private val eventCheckIn = MutableLiveData<Boolean>()

    private val viewState = MutableLiveData<ViewStateEnum>(ViewStateEnum.LOADING)

    fun getEventList(): LiveData<List<Event>> {
        return eventList
    }

    fun getEventSelected(): LiveData<Event> {
        return eventSelected
    }

    fun getViewState(): LiveData<ViewStateEnum> {
        return viewState
    }

    fun getEventCheckIn(): LiveData<Boolean> {
        return eventCheckIn
    }

    fun onTryAgainClick() {
        fetchEventList()
    }

    fun onCheckIn(checkIn: CheckIn) {
        viewState.postValue(ViewStateEnum.LOADING)
        GlobalScope.launch {
            when (val checkInData = postEventCheckInUseCase.execute(
                PostEventCheckInUseCaseInput(
                    checkInMapper.mapModelToData(checkIn)
                )
            )) {
                is DataWrapper.Success<Boolean> -> {
                    viewState.postValue(ViewStateEnum.SUCCESS)
                    eventCheckIn.postValue(checkInData.value!!)
                }
                else -> viewState.postValue(ViewStateEnum.FAILED)
            }
            viewState.postValue(ViewStateEnum.SUCCESS)
        }
    }

    fun onEventItemCLick(event: Event) {
        eventSelected.postValue(event)
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
