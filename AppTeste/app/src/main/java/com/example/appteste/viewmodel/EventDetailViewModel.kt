package com.example.appteste.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appteste.enum.Status
import com.example.appteste.model.CheckIn
import com.example.appteste.model.Event
import com.example.appteste.network.MainNetwork

class EventDetailViewModel(val mainNetwork: MainNetwork): ViewModel() {

    val showError = MutableLiveData<Boolean>()
    val showSucess = MutableLiveData<Boolean>()
    val showLoader = MutableLiveData<Boolean>()

    fun postCheckIn(checkIn: CheckIn) {
        mainNetwork.postCheckIn(checkIn) { result ->
            when(result.status) {
                Status.SUCCESS -> {
                    showSucess.postValue(true)
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