package br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn.PostEventCheckInUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn.PostEventCheckInUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.testShared.MockServiceInterceptorWithString
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.checkInEmail
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.checkInEventId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.checkInName
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
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class PostEventCheckInUseCaseTest : KoinTest {
    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val useCase: PostEventCheckInUseCase by inject()

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
    fun `test PostEventCheckInUseCase success`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithString(
                        "{\"code\": \"200\"}",
                        HttpURLConnection.HTTP_OK
                    )
                }
            }
        )
        runBlocking {
            when (val value = useCase.execute(
                PostEventCheckInUseCaseInput(
                    CheckInData(
                        checkInEventId,
                        checkInName,
                        checkInEmail
                    )
                )
            )) {
                is DataWrapper.NetworkError -> throw value.error
                is DataWrapper.Success<Boolean> -> {
                    Assert.assertTrue(value.value)
                }
                else -> throw RuntimeException()
            }
        }
    }
}