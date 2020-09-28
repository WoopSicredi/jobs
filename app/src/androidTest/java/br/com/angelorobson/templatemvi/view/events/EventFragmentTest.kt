package br.com.angelorobson.templatemvi.view.events

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import br.com.angelorobson.templatemvi.R
import br.com.angelorobson.templatemvi.di.TestComponent
import br.com.angelorobson.templatemvi.utils.FileUtils
import br.com.angelorobson.templatemvi.utils.TestIdlingResource
import br.com.angelorobson.templatemvi.utils.TestUtils
import br.com.angelorobson.templatemvi.utils.withRecyclerView
import br.com.angelorobson.templatemvi.view.component
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class EventFragmentTest {

    private val mockWebServer = MockWebServer()
    var idlingResource: TestIdlingResource? = null
    private var scenario: FragmentScenario<EventFragment>? = null

    @Before
    fun setUp() {
        val mockResponse = MockResponse()
                .setBody(FileUtils.getJson("json/events/events.json"))
        mockResponse.setResponseCode(200)
        mockWebServer.enqueue(mockResponse)
        mockWebServer.start(8500)

        scenario = launchFragmentInContainer<EventFragment>(
                themeResId = R.style.Theme_MaterialComponents_Light_NoActionBar
        )

        scenario?.onFragment { fragment ->
            idlingResource =
                    ((fragment.activity!!.component as TestComponent).idlingResource() as TestIdlingResource)
            IdlingRegistry.getInstance().register(idlingResource!!.countingIdlingResource)
        }

    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource!!.countingIdlingResource)
        mockWebServer.close()
    }

    @Test
    fun initialViews() {
        onView(ViewMatchers.withId(R.id.event_recycler_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkItemsInRecyclerView() {
        TestUtils.waitEspresso(2000)

        onView(withRecyclerView(R.id.event_recycler_view)
                .atPositionOnView(0, R.id.iv_post_image))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withRecyclerView(R.id.event_recycler_view)
                .atPositionOnView(0, R.id.tv_title))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withRecyclerView(R.id.event_recycler_view)
                .atPositionOnView(0, R.id.iv_clock))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withRecyclerView(R.id.event_recycler_view)
                .atPositionOnView(0, R.id.tv_date))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withRecyclerView(R.id.event_recycler_view)
                .atPositionOnView(0, R.id.tv_subtitle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

}