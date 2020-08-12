package br.com.nerdrapido.mvvmmockapiapp.di.modules

import br.com.nerdrapido.mvvmmockapiapp.remote.network.NetworkController
import br.com.nerdrapido.mvvmmockapiapp.remote.network.ServiceInterceptor
import okhttp3.Interceptor
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
object RemoteModule {

    /**
     * Módulo da camada Remote.
     */
    val remoteModule = module {
        single<Interceptor>(override = true) { ServiceInterceptor() }
        single {NetworkController(get())}
    }
}
