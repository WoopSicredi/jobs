package br.com.nerdrapido.mvvmmockapiapp.data.mapper

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.remote.model.PersonResponse
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
class PersonDataMapperTest : KoinTest {

    val id = "3"
    val eventId = "3"
    val name = "name 3"
    val picture = "picture 3"

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val personDataMapper : PersonDataMapper by inject()

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
    fun `test people map from remote to data`() {
        val peopleResponse = PersonResponse(id, eventId, name, picture)
        val peopleData = personDataMapper.mapRemoteToData(peopleResponse)
        Assert.assertEquals(id, peopleData.id)
        Assert.assertEquals(eventId, peopleData.eventId)
        Assert.assertEquals(name, peopleData.name)
        Assert.assertEquals(picture, peopleData.picture)
    }

    @Test
    fun `test people map from remote to data with null picture`() {
        val peopleResponse = PersonResponse(id, eventId, name)
        val peopleData = personDataMapper.mapRemoteToData(peopleResponse)
        Assert.assertEquals(id, peopleData.id)
        Assert.assertEquals(eventId, peopleData.eventId)
        Assert.assertEquals(name, peopleData.name)
        Assert.assertNull(peopleData.picture)
    }
}