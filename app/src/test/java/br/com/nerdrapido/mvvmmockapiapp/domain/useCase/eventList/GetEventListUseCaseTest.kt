package br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock
import br.com.nerdrapido.mvvmmockapiapp.testShared.MockServiceInterceptorWithFile
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
import java.net.HttpURLConnection

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class GetEventListUseCaseTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val useCase: GetEventListUseCase by inject()

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
    fun `test getEventListUseCase success`() {
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
            when (val itens = useCase.execute(GetEventListUseCaseInput())) {
                is DataWrapper.NetworkError -> throw itens.error
                is DataWrapper.Success<List<EventData>> -> {
                    Assert.assertEquals(3, itens.value.count())
                    val itemResponse = itens.value[2]
                    Assert.assertEquals(RemoteModelMock.date, itemResponse.date)
                    Assert.assertEquals(RemoteModelMock.description, itemResponse.description)
                    Assert.assertEquals(RemoteModelMock.image, itemResponse.image)
                    Assert.assertEquals(
                        RemoteModelMock.longitude,
                        itemResponse.longitude,
                        0.0000001
                    )
                    Assert.assertEquals(RemoteModelMock.latitude, itemResponse.latitude, 0.0000001)
                    Assert.assertEquals(RemoteModelMock.price, itemResponse.price)
                    Assert.assertEquals(RemoteModelMock.title, itemResponse.title)
                    Assert.assertEquals(RemoteModelMock.eventId, itemResponse.id)
                    Assert.assertEquals(1, itemResponse.cupons.count())
                    Assert.assertEquals(1, itemResponse.people.count())
                }
                else -> throw RuntimeException()
            }
        }
    }
}