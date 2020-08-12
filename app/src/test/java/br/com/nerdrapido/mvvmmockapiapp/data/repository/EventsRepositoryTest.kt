package br.com.nerdrapido.mvvmmockapiapp.data.repository

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.repository.event.EventRepository
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.testShared.MockServiceInterceptorWithException
import br.com.nerdrapido.mvvmmockapiapp.testShared.MockServiceInterceptorWithString
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import java.net.HttpURLConnection.HTTP_OK

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class EventsRepositoryTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val eventRepository: EventRepository by inject()

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
    fun `test get getEventList Network exception`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithException(Throwable())
                }
            }
        )

        runBlocking {
            when (val itens = eventRepository.getEventList()) {
                is DataWrapper.NetworkError -> {
                    Assert.assertNotNull(itens.error)
                }
                else -> throw RuntimeException()
            }
        }
    }

    @Test
    fun `test get getEventList Generic exception`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithString("{}", HTTP_OK)
                }
            }
        )

        runBlocking {
            when (val itens = eventRepository.getEventList()) {
                is DataWrapper.GenericError -> {
                    Assert.assertNotNull(itens.error)
                }
                else -> throw RuntimeException()
            }
        }
    }
}