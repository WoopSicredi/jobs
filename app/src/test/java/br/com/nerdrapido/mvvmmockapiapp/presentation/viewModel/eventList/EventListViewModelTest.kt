package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.eventList

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Event
import br.com.nerdrapido.mvvmmockapiapp.testShared.MockServiceInterceptorWithException
import br.com.nerdrapido.mvvmmockapiapp.testShared.MockServiceInterceptorWithString
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyList
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class EventListViewModelTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var dataObserver: Observer<List<Event>>

    @Mock
    lateinit var stateObserver: Observer<ViewStateEnum>

    val viewModel: EventListViewModel by inject()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
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
        viewModel.getEventList().removeObserver { }
        viewModel.getViewState().removeObserver { }
    }


    @Test
    fun `test fetch data success`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithString(
                        "[]",
                        HttpURLConnection.HTTP_OK
                    )
                }
            }
        )

        runBlocking {
            viewModel.getEventList().observeForever(dataObserver)
            viewModel.getViewState().observeForever(stateObserver)
            verify(
                stateObserver,
                Mockito.timeout(10000)
            ).onChanged(ViewStateEnum.LOADING)
            verify(
                stateObserver,
                Mockito.timeout(10000)
            ).onChanged(ViewStateEnum.SUCCESS)
            verify(
                dataObserver,
                Mockito.timeout(10000)
            ).onChanged(ArgumentMatchers.anyList())
        }
    }

    @Test
    fun `test fetch data body error`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithString(
                        "{}",
                        HttpURLConnection.HTTP_OK
                    )
                }
            }
        )

        runBlocking {
            viewModel.getEventList().observeForever(dataObserver)
            viewModel.getViewState().observeForever(stateObserver)
            verify(
                stateObserver,
                Mockito.timeout(10000)
            ).onChanged(ViewStateEnum.LOADING)
            verify(
                stateObserver,
                Mockito.timeout(10000)
            ).onChanged(ViewStateEnum.FAILED)
            verify(
                dataObserver,
                Mockito.timeout(10000).atLeast(0)
            ).onChanged(ArgumentMatchers.anyList())
        }
    }

    @Test
    fun `test fetch data failure`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithException(
                        IOException()
                    )
                }
            }
        )

        runBlocking {
            viewModel.getEventList().observeForever(dataObserver)
            viewModel.getViewState().observeForever(stateObserver)
            verify(
                stateObserver,
                Mockito.timeout(10000)
            ).onChanged(ViewStateEnum.LOADING)
            verify(
                stateObserver,
                Mockito.timeout(10000)
            ).onChanged(ViewStateEnum.FAILED)
            verify(
                dataObserver,
                Mockito.timeout(10000).atLeast(0)
            ).onChanged(ArgumentMatchers.anyList())
        }
    }
}