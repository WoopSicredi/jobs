package br.com.angelorobson.templatemvi

import br.com.angelorobson.templatemvi.di.DaggerTestComponent
import br.com.angelorobson.templatemvi.view.App

class AndroidTestApplication: App() {


    override val component by lazy {
        DaggerTestComponent.builder()
                .context(this)
                .build()
    }
}