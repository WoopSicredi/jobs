package br.com.nerdrapido.mvvmmockapiapp.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.runner.AndroidJUnit4
import br.com.nerdrapido.mvvmmockapiapp.testShared.MockServiceInterceptorWithString
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.eventListJson
import br.com.nerdrapido.mvvmmockapiapp.ui.view.eventList.EventListActivity
import okhttp3.Interceptor
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
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
        scenario.onActivity { activity ->

        }
    }
}