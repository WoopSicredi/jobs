package br.com.micaelpimentel.sicredevent.di

import br.com.micaelpimentel.sicredevent.api.RetrofitClient
import br.com.micaelpimentel.sicredevent.api.service.CheckinService
import br.com.micaelpimentel.sicredevent.api.service.EventService
import br.com.micaelpimentel.sicredevent.viewmodel.EventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<RetrofitClient> { RetrofitClient() }

    single<EventService> { get<RetrofitClient>().createEventService() }
    single<CheckinService> { get<RetrofitClient>().createCheckinService() }

    viewModel { EventsViewModel(get<EventService>(), get<CheckinService>()) }
}