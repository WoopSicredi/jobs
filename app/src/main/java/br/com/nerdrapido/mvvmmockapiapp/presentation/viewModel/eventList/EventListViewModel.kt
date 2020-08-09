package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.eventList

import androidx.lifecycle.MutableLiveData
import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.base.BaseViewModel

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
interface EventListViewModel : BaseViewModel {

    val eventListStateLiveData: MutableLiveData<EventListViewState>

    fun fetchEventList()
}