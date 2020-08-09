package br.com.nerdrapido.mvvmmockapiapp.data.mapper

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.remote.model.EventResponse
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.date
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.description
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.eventId
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.eventJson
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.image
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.latitude
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.longitude
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.price
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.title
import com.google.gson.Gson
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
class EventMapperTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val eventMapper: EventDataMaper by inject()

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
    fun `test item hydratation from json`() {
        val itenResponse = Gson().fromJson<EventResponse>(eventJson, EventResponse::class.java)
        val itemData = eventMapper.mapRemoteToData(itenResponse)
        Assert.assertEquals(date, itemData.date)
        Assert.assertEquals(description, itemData.description)
        Assert.assertEquals(image, itemData.image)
        Assert.assertEquals(longitude, itemData.longitude, 0.0000001)
        Assert.assertEquals(latitude, itemData.latitude, 0.0000001)
        Assert.assertEquals(price, itemData.price)
        Assert.assertEquals(title, itemData.title)
        Assert.assertEquals(eventId, itemData.id)
        Assert.assertEquals(1, itemData.cupons.count())
        Assert.assertEquals(1, itemData.people.count())
    }
}