package br.com.nerdrapido.mvvmmockapiapp.di.modules

import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.eventList.EventListViewModel
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 24/06/2020
 *
 * Inicialização do módulo de injeção de view models.
 */
object PresentationModule {

    /**
     * Módulo da camada Presenter.
     */
    val getPresenterModule = module {
        factory { EventListViewModel(get()) }
    }
}
