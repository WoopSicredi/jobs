package br.com.nerdrapido.mvvmmockapiapp.di

import android.app.Application
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.koin.core.context.stopKoin


/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class InjectionTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    @Test
    fun `test koin startup`() {
        startKoin {
            androidContext(context)
            modules(
                MainModule.module
            )
        }
    }


    @After
    fun tearDown() {
        stopKoin()
    }
}
