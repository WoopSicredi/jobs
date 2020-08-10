package br.com.nerdrapido.mvvmmockapiapp.presentation.model

import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner

/**
 * Created By FELIPE GUSBERTI @ 10/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class PersonTest : KoinTest {

    @Test
    fun `test PersonTest construction`() {
        val personTest = Person(
            RemoteModelMock.personId,
            RemoteModelMock.eventId,
            RemoteModelMock.name,
            RemoteModelMock.picture
        )
        Assert.assertEquals(RemoteModelMock.personId, personTest.id)
        Assert.assertEquals(RemoteModelMock.eventId, personTest.eventId)
        Assert.assertEquals(RemoteModelMock.name, personTest.name)
        Assert.assertEquals(RemoteModelMock.picture, personTest.picture)
    }

    @Test
    fun `test PersonTest default construction`() {
        val personTest = Person(
            RemoteModelMock.personId,
            RemoteModelMock.eventId,
            RemoteModelMock.name
        )
        Assert.assertEquals(RemoteModelMock.personId, personTest.id)
        Assert.assertEquals(RemoteModelMock.eventId, personTest.eventId)
        Assert.assertEquals(RemoteModelMock.name, personTest.name)
        Assert.assertNull(personTest.picture)
    }

}