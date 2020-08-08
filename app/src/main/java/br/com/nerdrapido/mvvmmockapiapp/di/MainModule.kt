package br.com.nerdrapido.mvvmmockapiapp.di

import br.com.nerdrapido.mvvmmockapiapp.di.modules.CacheModule
import br.com.nerdrapido.mvvmmockapiapp.di.modules.DataModule
import br.com.nerdrapido.mvvmmockapiapp.di.modules.DomainModule
import br.com.nerdrapido.mvvmmockapiapp.di.modules.PresentationModule
import br.com.nerdrapido.mvvmmockapiapp.di.modules.RemoteModule

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 *
 * Classe de obtenção do(s) módulo(s) para injeção pelo koin.
 */
object MainModule {

    val module = listOf(
        CacheModule.cacheModule,
        RemoteModule.remoteModule,
        DataModule.dataModule,
        DomainModule.domainModule,
        PresentationModule.getPresenterModule
    )
}