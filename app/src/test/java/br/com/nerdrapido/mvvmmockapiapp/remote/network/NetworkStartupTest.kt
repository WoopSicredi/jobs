package br.com.nerdrapido.mvvmmockapiapp.remote.network

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class NetworkStartupTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val networkController: NetworkController by inject()


    @Before
    fun setUp() {
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


    @Test
    fun `inject networkTest`() {
        assert(networkController.retrofit.baseUrl() != null)
    }
}