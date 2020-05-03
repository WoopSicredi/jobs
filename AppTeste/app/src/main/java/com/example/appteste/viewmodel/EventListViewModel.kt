package com.example.appteste.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteste.enum.Status
import com.example.appteste.model.Event
import com.example.appteste.network.MainNetwork

class EventListViewModel(val mainNetwork: MainNetwork): ViewModel() {

    val showError = MutableLiveData<Boolean>()
    val showLoader = MutableLiveData<Boolean>()
    val eventList = MutableLiveData<ArrayList<Event>>()

    fun getEventList() {
        mainNetwork.getEventList { result ->
            when(result.status) {
                Status.SUCCESS -> {
                    eventList.postValue(result.data)
                    showLoader.postValue(false)
                }
                Status.LOADING -> showLoader.postValue(true)
                Status.ERROR -> {
                    showError.postValue(true)
                    showLoader.postValue(false)
                }
            }
        }
    }

}