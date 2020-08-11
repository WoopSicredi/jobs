package br.com.nerdrapido.mvvmmockapiapp.di.modules

import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.cupom.CupomModelMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.cupom.CupomModelMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.event.EventModelMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.event.EventModelMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.person.PersonModelMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.person.PersonModelMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event.EventViewModel
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
        single<PersonModelMapper> { PersonModelMapperImpl() }
        single<CupomModelMapper> { CupomModelMapperImpl() }
        single<EventModelMapper> { EventModelMapperImpl(get(), get()) }
        factory { EventViewModel(get(), get()) }
    }
}
