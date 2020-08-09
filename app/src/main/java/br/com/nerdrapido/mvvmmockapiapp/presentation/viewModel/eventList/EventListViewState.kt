package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.eventList

import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
data class EventListViewState(

    val state: ViewStateEnum = ViewStateEnum.LOADING,

    val error: Throwable? = null,

    val eventList: List<EventData> = emptyList()
)