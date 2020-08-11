package br.com.nerdrapido.mvvmmockapiapp.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.event.EventDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.DataWrapper
import br.com.nerdrapido.mvvmmockapiapp.data.model.EventData
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCase
import br.com.nerdrapido.mvvmmockapiapp.domain.useCase.eventList.GetEventListUseCaseInput
import br.com.nerdrapido.mvvmmockapiapp.remote.model.EventResponse
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.description
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.eventListJson
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.price
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.title
import br.com.nerdrapido.mvvmmockapiapp.ui.view.event.EventActivity
import br.com.nerdrapido.mvvmmockapiapp.ui.view.event.EventListAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import java.io.IOException
import java.lang.reflect.Type

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
@RunWith(JUnit4ClassRunner::class)
class EventActivityTest : KoinTest {

    private val valor = "Valor: R$ 19,99"

    private val eventDataMapper: EventDataMapper by inject()


    private lateinit var getEventListUseCaseOutput: DataWrapper<List<EventData>>

    private val getEventListUseCase = object : GetEventListUseCase {
        override suspend fun execute(input: GetEventListUseCaseInput): DataWrapper<List<EventData>> {
            return getEventListUseCaseOutput
        }
    }

    @Test
    fun test_EventListActivity_instantiation() {
        getEventListUseCaseOutput = getDataSuccess()
        loadKoinModules(
            module {
                single<GetEventListUseCase>(override = true) { getEventListUseCase }
            }
        )
        val scenario: ActivityScenario<EventActivity> =
            ActivityScenario.launch(EventActivity::class.java)
        scenario.onActivity {}
        onView(withId(R.id.eventListRv)).check(matches(isDisplayed()))
        waitViewAppear(onView(withText(title)))
        onView(withId(R.id.eventListRv)).perform(
            RecyclerViewActions.scrollTo<EventListAdapter.ViewHolder>(
                withChild(withText(title))
            )
        )
        onView(withText(valor)).check(matches(isDisplayed()))
        onView(withText(valor)).perform(click())

    }

    @Test
    fun test_EventListActivity_api_error() {
        getEventListUseCaseOutput =
            DataWrapper.NetworkError(IOException("test_EventListActivity_api_error"))

        loadKoinModules(
            module {
                single<GetEventListUseCase>(override = true) { getEventListUseCase }
            }
        )

        val scenario: ActivityScenario<EventActivity> =
            ActivityScenario.launch(EventActivity::class.java)

        scenario.onActivity {}
        waitViewAppear(onView(withText("Houve um erro ao carregar o conteúdo.")))
        onView(withText("Erro!")).check(matches(isDisplayed()))
        onView(withText("Tentar novamente")).check(matches(isDisplayed()))

        getEventListUseCaseOutput = getDataSuccess()
        onView(withText("Tentar novamente")).perform(click())
        waitViewAppear(onView(withId(R.id.eventListRv)))
        onView(withId(R.id.eventListRv)).perform(
            RecyclerViewActions.scrollTo<EventListAdapter.ViewHolder>(
                withChild(withText(title))
            )
        )
        onView(withText(valor)).check(matches(isDisplayed()))
    }

    @Test
    fun test_if_fragment_event_activity_is_called_when_item_is_clicked() {
        getEventListUseCaseOutput = getDataSuccess()

        loadKoinModules(
            module {
                single<GetEventListUseCase>(override = true) { getEventListUseCase }
            }
        )

        val scenario: ActivityScenario<EventActivity> =
            ActivityScenario.launch(EventActivity::class.java)
        scenario.onActivity {}
        waitViewAppear(onView(withText(title)))
        onView(withId(R.id.eventListRv)).perform(
            RecyclerViewActions.scrollTo<EventListAdapter.ViewHolder>(
                withChild(withText(title))
            )
        )
        onView(withText(valor)).perform(click())
        onView(withId(R.id.eventCoverIv)).check(matches(isDisplayed()))
        onView(allOf(withText(title), withId(R.id.infoTv))).check(
            matches(
                isDisplayed()
            )
        )
        onView(
            allOf(withText(description), withId(R.id.infoTv))
        ).check(
            matches(
                isDisplayingAtLeast(10)
            )
        )
        onView(
            allOf(
                withText("Check-in"),
                withId(R.id.checkInBt)
            )
        ).check(
            matches(
                isDisplayed()
            )
        )
        onView(
            allOf(
                withText(price.toString()),
                withId(R.id.infoTv)
            )
        ).check(
            matches(
                isDisplayed()
            )
        )
    }

    private fun waitViewAppear(viewInteraction: ViewInteraction) {
        var i = 500
        var throwable: Throwable? = null
        while (i > 0) {
            try {
                viewInteraction.check(matches(isDisplayed()))
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

    private fun getDataSuccess(): DataWrapper<List<EventData>> {
        val type: Type = object : TypeToken<List<EventResponse>>() {}.type
        val remote = Gson().fromJson<List<EventResponse>>(eventListJson, type)
        return DataWrapper.Success(
            eventDataMapper.mapRemoteToDataList(
                remote
            )
        )
    }
}
