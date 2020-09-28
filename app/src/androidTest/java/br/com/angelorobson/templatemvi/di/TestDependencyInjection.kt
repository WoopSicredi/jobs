package br.com.angelorobson.templatemvi.di

import android.content.Context
import br.com.angelorobson.templatemvi.utils.TestIdlingResource
import br.com.angelorobson.templatemvi.view.utils.IdlingResource
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class, ApiModule::class, TestModule::class])
interface TestComponent : ApplicationComponent {

    fun idlingResource(): IdlingResource

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): TestComponent
    }
}

@Module
object TestModule {

    @Provides
    @Singleton
    @JvmStatic
    fun idlingResource(): IdlingResource = TestIdlingResource()
}