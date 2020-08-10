package br.com.nerdrapido.mvvmmockapiapp.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.event.EventDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.remote.model.EventResponse
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.eventListJson
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.title
import br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList.EventListActivity
import br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList.EventListAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import java.io.IOException
import java.lang.reflect.Type

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
@RunWith(JUnit4ClassRunner::class)
class EventListActivityTest : KoinTest {

    private val eventDataMapper: EventDataMapper by inject()

    private lateinit var getEventListUseCaseOutput: DataWrapper<List<EventData>>

    private val getEventListUseCase = object : GetEventListUseCase {
        override suspend fun execute(input: GetEventListUseCaseInput): DataWrapper<List<EventData>> {
            return getEventListUseCaseOutput
        }
    }

    @Test
    fun test_EventListActivity_instantiation() {
        loadKoinModules(
            module {
                single<GetEventListUseCase>(override = true) { getEventListUseCase }
            }
        )
        val type: Type = object : TypeToken<List<EventResponse>>() {}.type
        val jsonRemote = Gson().fromJson<List<EventResponse>>(eventListJson, type)
        getEventListUseCaseOutput =
            DataWrapper.Success(eventDataMapper.mapRemoteToDataList(jsonRemote))

        val scenario: ActivityScenario<EventListActivity> =
            ActivityScenario.launch(EventListActivity::class.java)

        scenario.onActivity {

        }
        onView(withId(R.id.eventListRv)).check(matches(isDisplayed()))
        var i = 500
        var throwable: Throwable? = null
        while (i > 0) {
            try {
                onView(withId(R.id.eventListRv)).perform(
                    scrollTo<EventListAdapter.ViewHolder>(
                        withChild(withText(title))
                    )
                )
                onView(withText("Valor: R$ 19,99")).check(matches(isDisplayed()))
                break
            } catch (t: Throwable) {
                i--
                throwable = t
                Thread.sleep(10)
            }
        }
        if (i == 0) {
            throw throwable!!
        }

    }

    @Test
    fun test_EventListActivity_api_error() {
        loadKoinModules(
            module {
                single<GetEventListUseCase>(override = true) { getEventListUseCase }
            }
        )
        getEventListUseCaseOutput =
            DataWrapper.NetworkError(IOException("test_EventListActivity_api_error"))
        val scenario: ActivityScenario<EventListActivity> =
            ActivityScenario.launch(EventListActivity::class.java)

        scenario.onActivity {

        }
        var i = 500
        var throwable: Throwable? = null
        while (i > 0) {
            try {
                onView(withText("Houve um erro ao carregar o conteúdo.")).check(matches(isDisplayed()))
                onView(withText("Erro!")).check(matches(isDisplayed()))
                onView(withText("Tentar novamente")).check(matches(isDisplayed()))
                break
            } catch (t: Throwable) {
                i--
                throwable = t
                Thread.sleep(10)
            }
        }
        if (i == 0) {
            throw throwable!!
        }

        val type: Type = object : TypeToken<List<EventResponse>>() {}.type
        val jsonRemote = Gson().fromJson<List<EventResponse>>(eventListJson, type)
        getEventListUseCaseOutput =
            DataWrapper.Success(eventDataMapper.mapRemoteToDataList(jsonRemote))
        onView(withText("Tentar novamente")).perform(click())
        i = 500
        while (i > 0) {
            try {
                onView(withId(R.id.eventListRv)).check(matches(isDisplayed()))
                break
            } catch (t: Throwable) {
                i--
                throwable = t
                Thread.sleep(10)
            }
        }
        if (i == 0) {
            throw throwable!!
        }


    }
}
