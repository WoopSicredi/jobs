package br.com.nerdrapido.mvvmmockapiapp.remote.model

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.helpers.json.JsonMapper
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class PersonResponseTest : KoinTest {

    val id = "3"
    val eventId = "3"
    val name = "name 3"
    val picture = "picture 3"
    val cuponJson = "{\"id\":\"3\",\"eventId\":\"3\",\"name\":\"name 3\",\"picture\":\"picture 3\"}"

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val jsonMapper: JsonMapper by inject()

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
    fun `test people hydratation from json`() {
        val peopleResponse = jsonMapper.fromString(cuponJson, PersonResponse::class.java)
        Assert.assertEquals(id, peopleResponse.id)
        Assert.assertEquals(eventId, peopleResponse.eventId)
        Assert.assertEquals(name, peopleResponse.name)
        Assert.assertEquals(picture, peopleResponse.picture)
    }

    @Test
    fun `test people hydratation`() {
        val peopleResponse = PersonResponse(id, eventId, name, picture)
        Assert.assertEquals(id, peopleResponse.id)
        Assert.assertEquals(eventId, peopleResponse.eventId)
        Assert.assertEquals(name, peopleResponse.name)
        Assert.assertEquals(picture, peopleResponse.picture)
    }

    @Test
    fun `test people hydratation with null picture`() {
        val peopleResponse = PersonResponse(id, eventId, name)
        Assert.assertEquals(id, peopleResponse.id)
        Assert.assertEquals(eventId, peopleResponse.eventId)
        Assert.assertEquals(name, peopleResponse.name)
        Assert.assertNull(peopleResponse.picture)
    }
}