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
    fun `test get getEventList success`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithFile(
                        "item_list_success.json",
                        HttpURLConnection.HTTP_OK
                    )
                }
            }
        )
        runBlocking {
            when (val itens = eventRepository.getEventList()) {
                is DataWrapper.Error -> throw itens.error
                is DataWrapper.Success<List<EventData>> -> {
                    Assert.assertEquals(3, itens.value.count())
                    val itemResponse = itens.value[2]
                    Assert.assertEquals(date, itemResponse.date)
                    Assert.assertEquals(description, itemResponse.description)
                    Assert.assertEquals(image, itemResponse.image)
                    Assert.assertEquals(longitude, itemResponse.longitude, 0.0000001)
                    Assert.assertEquals(latitude, itemResponse.latitude, 0.0000001)
                    Assert.assertEquals(price, itemResponse.price)
                    Assert.assertEquals(title, itemResponse.title)
                    Assert.assertEquals(eventId, itemResponse.id)
                    Assert.assertEquals(1, itemResponse.cupons.count())
                    Assert.assertEquals(1, itemResponse.people.count())
                }
                else -> throw RuntimeException()
            }
        }
    }

    @Test
    fun `test get getEventList IO exception error`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithException(IOException())
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