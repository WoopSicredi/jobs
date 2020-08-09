package br.com.nerdrapido.mvvmmockapiapp.data.repository

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.data.repository.event.EventRepository
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.date
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.description
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.eventId
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.image
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.latitude
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.longitude
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.price
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.title
import br.com.nerdrapido.mvvmmockapiapp.remote.network.MockServiceInterceptorWithException
import br.com.nerdrapido.mvvmmockapiapp.remote.network.MockServiceInterceptorWithFile
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
import java.io.IOException
import java.net.HttpURLConnection

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
    fun `test get getEventList Generic exception`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithException(Throwable())
                }
            }
        )

        runBlocking {
            when (val itens = eventRepository.getEventList()) {
                is DataWrapper.Error -> {
                    Assert.assertNotNull(itens.error)
                }
                else -> throw RuntimeException()
            }
        }
    }
}