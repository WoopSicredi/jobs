package br.com.nerdrapido.mvvmmockapiapp.ui.application

import android.app.Application
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
class StartApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inicialização do Timber para árvore de logs
        Timber.plant(Timber.DebugTree())

        // Inicialização do koin. Deve ser inicializado aqui.
        startKoin {
            // declara o contexto do Android.
            androidContext(this@StartApplication)
            // declara o(s) módulo(s).
            modules(MainModule.module)
        }
    }
}
