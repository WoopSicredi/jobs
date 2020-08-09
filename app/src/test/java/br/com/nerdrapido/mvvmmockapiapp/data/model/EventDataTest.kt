package br.com.nerdrapido.mvvmmockapiapp.data.model

import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.date
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.description
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.eventId
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.image
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.latitude
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.longitude
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.price
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.title
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class EventDataTest : KoinTest {

    @Test
    fun `test item construction`() {
        val itemResponse = EventData(
            date,
            description,
            image,
            longitude,
            latitude,
            price,
            title,
            eventId,
            emptyList(),
            emptyList()
        )
        Assert.assertEquals(date, itemResponse.date)
        Assert.assertEquals(description, itemResponse.description)
        Assert.assertEquals(image, itemResponse.image)
        Assert.assertEquals(longitude, itemResponse.longitude, 0.0000001)
        Assert.assertEquals(latitude, itemResponse.latitude, 0.0000001)
        Assert.assertEquals(price, itemResponse.price)
        Assert.assertEquals(title, itemResponse.title)
        Assert.assertEquals(eventId, itemResponse.id)
        Assert.assertEquals(0, itemResponse.cupons.count())
        Assert.assertEquals(0, itemResponse.people.count())
    }

    @Test
    fun `test item default construction`() {
        val itemResponse = EventData(
            date,
            description,
            image,
            longitude,
            latitude,
            price,
            title,
            eventId
        )
        Assert.assertEquals(date, itemResponse.date)
        Assert.assertEquals(description, itemResponse.description)
        Assert.assertEquals(image, itemResponse.image)
        Assert.assertEquals(longitude, itemResponse.longitude, 0.0000001)
        Assert.assertEquals(latitude, itemResponse.latitude, 0.0000001)
        Assert.assertEquals(price, itemResponse.price)
        Assert.assertEquals(title, itemResponse.title)
        Assert.assertEquals(eventId, itemResponse.id)
        Assert.assertEquals(0, itemResponse.cupons.count())
        Assert.assertEquals(0, itemResponse.people.count())
    }
}
