package br.com.angelorobson.templatemvi.view

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.angelorobson.templatemvi.di.ApplicationComponent
import br.com.angelorobson.templatemvi.di.DaggerRealComponent
import br.com.angelorobson.templatemvi.view.utils.BaseViewModel

import com.jakewharton.threetenabp.AndroidThreeTen
import kotlin.reflect.KClass

open class App : Application() {

    open val component: ApplicationComponent by lazy {
        DaggerRealComponent.builder()
                .context(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}

val Context.component: ApplicationComponent
    get() = (this.applicationContext as App).component

fun <T, M, E> Fragment.getViewModel(type: KClass<T>): BaseViewModel<M, E> where T : ViewModel, T : BaseViewModel<M, E> {
    val factory = this.requireContext().component.viewModelFactory()
    return ViewModelProvider(this, factory)[type.java]
}

fun <T, M, E> FragmentActivity.getViewModel(type: KClass<T>): BaseViewModel<M, E> where T : ViewModel, T : BaseViewModel<M, E> {
    val factory = this.applicationContext!!.component.viewModelFactory()
    return ViewModelProvider(this, factory)[type.java]
}
