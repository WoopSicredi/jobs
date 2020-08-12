package br.com.nerdrapido.mvvmmockapiapp.di.modules

import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn.PostEventCheckInUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn.PostEventCheckInUseCaseImpl
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCaseImpl
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
object DomainModule {

    /**
     * Módulo da camada Domain.
     */
    val domainModule = module {
        single<GetEventListUseCase>(override = true) { GetEventListUseCaseImpl(get()) }
        single<PostEventCheckInUseCase>(override = true) { PostEventCheckInUseCaseImpl(get()) }
    }
}
