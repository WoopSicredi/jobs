package br.com.nerdrapido.mvvmmockapiapp.presentation.model

import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock
import org.junit.Assert
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Created By FELIPE GUSBERTI @ 10/08/2020
 */
class EventTest : KoinTest {

    val data = "data data"

    @Test
    fun `test EventTest construction`() {
        val eventTest = Event(
            data,
            RemoteModelMock.description,
            RemoteModelMock.image,
            RemoteModelMock.longitude,
            RemoteModelMock.latitude,
            RemoteModelMock.price,
            RemoteModelMock.title,
            RemoteModelMock.eventId,
            emptyList(),
            emptyList()
        )
        Assert.assertEquals(data, eventTest.date)
        Assert.assertEquals(RemoteModelMock.description, eventTest.description)
        Assert.assertEquals(RemoteModelMock.image, eventTest.image)
        Assert.assertEquals(RemoteModelMock.longitude, eventTest.longitude, 0.0000001)
        Assert.assertEquals(RemoteModelMock.latitude, eventTest.latitude, 0.0000001)
        Assert.assertEquals(RemoteModelMock.price, eventTest.price)
        Assert.assertEquals(RemoteModelMock.title, eventTest.title)
        Assert.assertEquals(RemoteModelMock.eventId, eventTest.id)
        Assert.assertEquals(0, eventTest.cupons.count())
        Assert.assertEquals(0, eventTest.people.count())
    }

    @Test
    fun `test EventTest default construction`() {
        val eventTest = Event(
            data,
            RemoteModelMock.description,
            RemoteModelMock.image,
            RemoteModelMock.longitude,
            RemoteModelMock.latitude,
            RemoteModelMock.price,
            RemoteModelMock.title,
            RemoteModelMock.eventId
        )
        Assert.assertEquals(data, eventTest.date)
        Assert.assertEquals(RemoteModelMock.description, eventTest.description)
        Assert.assertEquals(RemoteModelMock.image, eventTest.image)
        Assert.assertEquals(RemoteModelMock.longitude, eventTest.longitude, 0.0000001)
        Assert.assertEquals(RemoteModelMock.latitude, eventTest.latitude, 0.0000001)
        Assert.assertEquals(RemoteModelMock.price, eventTest.price)
        Assert.assertEquals(RemoteModelMock.title, eventTest.title)
        Assert.assertEquals(RemoteModelMock.eventId, eventTest.id)
        Assert.assertEquals(0, eventTest.cupons.count())
        Assert.assertEquals(0, eventTest.people.count())
    }
}