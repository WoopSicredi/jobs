package com.example.appteste.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun getFactory(viewModel: ViewModel): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModel as T
        }
    }
}