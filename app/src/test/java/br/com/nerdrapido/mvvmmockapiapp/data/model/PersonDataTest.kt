package br.com.nerdrapido.mvvmmockapiapp.data.model

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.helpers.json.JsonMapper
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.eventId
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.name
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.personId
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.personJson
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.picture
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
class PersonDataTest : KoinTest {

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
    fun `test people hydratation`() {
        val peopleResponse = PersonData(personId, eventId, name, picture)
        Assert.assertEquals(personId, peopleResponse.id)
        Assert.assertEquals(eventId, peopleResponse.eventId)
        Assert.assertEquals(name, peopleResponse.name)
        Assert.assertEquals(picture, peopleResponse.picture)
    }

    @Test
    fun `test people hydratation with null picture`() {
        val peopleResponse = PersonData(personId, eventId, name)
        Assert.assertEquals(personId, peopleResponse.id)
        Assert.assertEquals(eventId, peopleResponse.eventId)
        Assert.assertEquals(name, peopleResponse.name)
        Assert.assertNull(peopleResponse.picture)
    }
}