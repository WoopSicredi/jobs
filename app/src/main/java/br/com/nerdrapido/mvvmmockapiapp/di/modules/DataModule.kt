package br.com.nerdrapido.mvvmmockapiapp.di.modules

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.*
import br.com.nerdrapido.mvvmmockapiapp.data.repository.EventRepository
import br.com.nerdrapido.mvvmmockapiapp.data.repository.EventRepositoryImpl
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
object DataModule {

    /**
     * Módulo da camada Data.
     */
    val dataModule = module {
        single<EventRepository> { EventRepositoryImpl(get()) }
        single<CupomDataMapper> { CupomDataMapperImpl() }
        single<PersonDataMapper> { PersonDataMapperImpl() }
        single<EventDataMaper> { EventDataMapperImpl(get(), get()) }
    }
}