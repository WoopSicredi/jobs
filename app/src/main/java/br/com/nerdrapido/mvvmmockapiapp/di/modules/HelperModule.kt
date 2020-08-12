package br.com.nerdrapido.mvvmmockapiapp.di.modules

import br.com.nerdrapido.mvvmmockapiapp.helpers.json.GsonImpl
import br.com.nerdrapido.mvvmmockapiapp.helpers.json.JsonMapper
import br.com.nerdrapido.mvvmmockapiapp.ui.helper.ViewHelper
import br.com.nerdrapido.mvvmmockapiapp.ui.helper.ViewHelperImpl
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
object HelperModule {

    /**
     * Módulo da camada Data.
     */
    val helperModule = module {
        single<JsonMapper> { GsonImpl() }
        single<ViewHelper> { ViewHelperImpl() }
    }
}