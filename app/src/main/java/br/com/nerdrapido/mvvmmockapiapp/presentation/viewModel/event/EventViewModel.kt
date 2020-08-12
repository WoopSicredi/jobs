package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private val eventCheckInSuccess = MutableLiveData<Boolean>()

    private val eventCheckInWanted = MutableLiveData<Boolean>()

    private val backPressed = MutableLiveData<Boolean>()

    private val eventCheckInNotValid = MutableLiveData<FieldRuleEnum>()

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

    fun getEventCheckInSuccess(): LiveData<Boolean> {
        return eventCheckInSuccess
    }

    fun getEventCheckInWanted(): LiveData<Boolean> {
        return eventCheckInWanted
    }

    fun getEventCheckInNotValid(): LiveData<FieldRuleEnum> {
        return eventCheckInNotValid
    }

    fun getBackPressed() :LiveData<Boolean> {
        return backPressed
    }

    fun onTryAgainClick() {
        fetchEventList()
    }

    fun onCheckInWanted() {
        eventCheckInWanted.postValue(true)
    }

    fun onCheckInRequested(name: String, email: String) {
        viewState.postValue(ViewStateEnum.LOADING)
        val event = eventSelected.value
        if (event == null) {
            // Se evento está nulo ocorreu um erro inesperado
            viewState.postValue(ViewStateEnum.FAILED)
            return
        }
        val checkIn = CheckIn(event.id, name, email)
        val valid = checkInIsValid(checkIn)
        if (valid != FieldRuleEnum.VALID) {
            eventCheckInNotValid.postValue(valid)
            viewState.postValue(ViewStateEnum.SUCCESS)
            return
        }
        GlobalScope.launch {
            when (val checkInData = postEventCheckInUseCase.execute(
                PostEventCheckInUseCaseInput(
                    checkInMapper.mapModelToData(checkIn)
                )
            )) {
                is DataWrapper.Success<Boolean> -> {
                    viewState.postValue(ViewStateEnum.SUCCESS)
                    eventCheckInSuccess.postValue(checkInData.value!!)
                }
                else -> {
                    eventCheckInSuccess.postValue(false)
                    viewState.postValue(ViewStateEnum.FAILED)
                }
            }
        }
    }

    private fun checkInIsValid(checkIn: CheckIn): FieldRuleEnum {
        if (checkIn.name.isBlank()) {
            return FieldRuleEnum.NAME_MISSING
        }
        if (checkIn.email.isBlank()) {
            return FieldRuleEnum.EMAIL_MISSING
        }
        return FieldRuleEnum.VALID
    }

    fun onEventItemCLick(event: Event) {
        eventSelected.postValue(event)
    }

    fun onBackPressed() {
        backPressed.postValue(true)
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

    enum class FieldRuleEnum {
        NAME_MISSING,
        EMAIL_MISSING,
        VALID
    }
}
