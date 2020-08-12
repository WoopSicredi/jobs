package br.com.nerdrapido.mvvmmockapiapp.presentation.viewModel.event

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.event.EventDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn.PostEventCheckInUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventCheckIn.PostEventCheckInUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.presentation.enums.ViewStateEnum
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.event.EventModelMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.Event
import br.com.nerdrapido.mvvmmockapiapp.remote.model.EventResponse
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
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
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import java.lang.reflect.Type

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class EventViewModelTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val eventDataMapper: EventDataMapper by inject()

    private val eventMapper: EventModelMapper by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var dataObserver: Observer<List<Event>>

    @Mock
    lateinit var stateObserver: Observer<ViewStateEnum>

    @Mock
    lateinit var checkInObserver: Observer<Boolean>

    private lateinit var getEventListUseCaseOutput: DataWrapper<List<EventData>>

    private lateinit var postEventCheckInUseCaseOutput: DataWrapper<Boolean>

    private val getEventListUseCase = object : GetEventListUseCase {
        override suspend fun execute(input: GetEventListUseCaseInput): DataWrapper<List<EventData>> {
            return getEventListUseCaseOutput
        }
    }

    private val postEventCheckInUseCase = object : PostEventCheckInUseCase {
        override suspend fun execute(input: PostEventCheckInUseCaseInput): DataWrapper<Boolean> =
            postEventCheckInUseCaseOutput
    }

    val viewModel: EventViewModel by inject()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            androidContext(context)
            modules(
                MainModule.module
            )
        }
        loadKoinModules(
            module {
                single<GetEventListUseCase>(override = true) { getEventListUseCase }
                single<PostEventCheckInUseCase>(override = true) { postEventCheckInUseCase }
            }
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test fetch data success`() {
        getEventListUseCaseOutput = DataWrapper.Success(emptyList())

        runBlocking {
            viewModel.getEventList().observeForever(dataObserver)
            viewModel.getViewState().observeForever(stateObserver)
            verify(
                dataObserver,
                Mockito.timeout(1000)
            ).onChanged(ArgumentMatchers.anyList())
        }
    }

    @Test
    fun `test fetch data body error`() {
        getEventListUseCaseOutput = DataWrapper.GenericError(Throwable())

        runBlocking {
            viewModel.getEventList().observeForever(dataObserver)
            viewModel.getViewState().observeForever(stateObserver)
            verify(
                stateObserver,
                Mockito.timeout(1000)
            ).onChanged(ViewStateEnum.LOADING)
            verify(
                stateObserver,
                Mockito.timeout(1000)
            ).onChanged(ViewStateEnum.FAILED)
            verify(
                dataObserver,
                Mockito.timeout(1000).atLeast(0)
            ).onChanged(ArgumentMatchers.anyList())
        }
    }

    @Test
    fun `test fetch data failure`() {
        getEventListUseCaseOutput = DataWrapper.NetworkError(Throwable())

        runBlocking {
            viewModel.getEventList().observeForever(dataObserver)
            viewModel.getViewState().observeForever(stateObserver)
            verify(
                stateObserver,
                Mockito.timeout(1000)
            ).onChanged(ViewStateEnum.LOADING)
            verify(
                stateObserver,
                Mockito.timeout(1000)
            ).onChanged(ViewStateEnum.FAILED)
            verify(
                dataObserver,
                Mockito.timeout(1000).atLeast(0)
            ).onChanged(ArgumentMatchers.anyList())
        }
    }

    @Test
    fun `test post check-in success`() {
        getEventListUseCaseOutput = getDataSuccess()
        postEventCheckInUseCaseOutput = DataWrapper.Success(true)

        runBlocking {
            viewModel.onEventItemCLick(
                eventMapper.mapDataToModel((getDataSuccess() as DataWrapper.Success).value[0])
            )
            viewModel.getViewState().observeForever(stateObserver)
            viewModel.getEventCheckInSuccess().observeForever(checkInObserver)
            viewModel.onCheckInRequested(
                RemoteModelMock.checkInName,
                RemoteModelMock.checkInEmail
            )
            verify(checkInObserver, Mockito.timeout(1000)).onChanged(true)
        }
    }

    @Test
    fun `test post check-in with unlikely null event`() {
        getEventListUseCaseOutput = getDataSuccess()
        postEventCheckInUseCaseOutput = DataWrapper.Success(true)

        runBlocking {
            viewModel.getViewState().observeForever(stateObserver)
            viewModel.getEventCheckInSuccess().observeForever(checkInObserver)
            viewModel.onCheckInRequested(
                RemoteModelMock.checkInName,
                RemoteModelMock.checkInEmail
            )
            verify(stateObserver, Mockito.timeout(1000)).onChanged(ViewStateEnum.FAILED)
        }
    }

    @Test
    fun `test post check-in failure`() {
        getEventListUseCaseOutput = getDataSuccess()
        postEventCheckInUseCaseOutput = DataWrapper.NetworkError(Throwable())

        runBlocking {
            viewModel.onEventItemCLick(
                eventMapper.mapDataToModel((getDataSuccess() as DataWrapper.Success).value[0])
            )
            viewModel.getViewState().observeForever(stateObserver)
            viewModel.getEventCheckInSuccess().observeForever(checkInObserver)
            viewModel.onCheckInRequested(
                RemoteModelMock.checkInName,
                RemoteModelMock.checkInEmail
            )
            verify(checkInObserver, Mockito.timeout(1000)).onChanged(false)
            verify(stateObserver, Mockito.timeout(1000)).onChanged(ViewStateEnum.FAILED)
        }
    }

    private fun getDataSuccess(): DataWrapper<List<EventData>> {
        val type: Type = object : TypeToken<List<EventResponse>>() {}.type
        val remote = Gson().fromJson<List<EventResponse>>(RemoteModelMock.eventListJson, type)
        return DataWrapper.Success(
            eventDataMapper.mapRemoteToDataList(
                remote
            )
        )
    }
}
