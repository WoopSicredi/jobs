package br.com.nerdrapido.mvvmmockapiapp.di

import br.com.nerdrapido.mvvmmockapiapp.di.modules.*

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 *
 * Classe de obtenção do(s) módulo(s) para injeção pelo koin.
 */
object MainModule {

    val module = listOf(
        HelperModule.helperModule,
        RemoteModule.remoteModule,
        DataModule.dataModule,
        DomainModule.domainModule,
        PresentationModule.getPresenterModule
    )
}