package br.com.nerdrapido.mvvmmockapiapp.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import br.com.nerdrapido.mvvmmockapiapp.R
import br.com.nerdrapido.mvvmmockapiapp.testShared.MockServiceInterceptorWithException
import br.com.nerdrapido.mvvmmockapiapp.testShared.MockServiceInterceptorWithString
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.eventListJson
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.title
import br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList.EventListActivity
import br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList.EventListAdapter
import okhttp3.Interceptor
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 */
@RunWith(AndroidJUnit4::class)
class EventListActivityTest {

    @Test
    fun test_EventListActivity_instantiation() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithString(
                        eventListJson,
                        HttpURLConnection.HTTP_OK
                    )
                }
            }
        )
        val scenario: ActivityScenario<EventListActivity> =
            ActivityScenario.launch(EventListActivity::class.java)

        scenario.onActivity {

        }
        onView(withId(R.id.eventListRv)).check(matches(isDisplayed()))
        var i = 100
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
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithException(IOException())
                }
            }
        )

        val scenario: ActivityScenario<EventListActivity> =
            ActivityScenario.launch(EventListActivity::class.java)

        scenario.onActivity {

        }
        var i = 100
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

    }
}