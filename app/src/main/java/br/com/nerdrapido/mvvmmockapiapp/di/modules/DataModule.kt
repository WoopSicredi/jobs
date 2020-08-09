package br.com.nerdrapido.mvvmmockapiapp.di.modules

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.cupom.CupomDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.cupom.CupomDataMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.event.EventDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.event.EventDataMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.person.PersonDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.person.PersonDataMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.data.repository.event.EventRepository
import br.com.nerdrapido.mvvmmockapiapp.data.repository.event.EventRepositoryImpl
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
object DataModule {

    /**
     * Módulo da camada Data.
     */
    val dataModule = module {
        single<EventRepository> { EventRepositoryImpl(get(), get()) }
        single<CupomDataMapper> { CupomDataMapperImpl() }
        single<PersonDataMapper> { PersonDataMapperImpl() }
        single<EventDataMapper> {
            EventDataMapperImpl(
                get(),
                get()
            )
        }
    }
}