package br.com.nerdrapido.mvvmmockapiapp.data.model

import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.eventId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.name
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.personId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.picture
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class PersonDataTest : KoinTest {

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