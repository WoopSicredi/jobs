package br.com.nerdrapido.mvvmmockapiapp.remote.model

import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.eventId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.personName
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.personId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.personJson
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.picture
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class PersonResponseTest : KoinTest {

    @Test
    fun `test people hydratation from json`() {
        val peopleResponse = Gson().fromJson(personJson, PersonResponse::class.java)
        Assert.assertEquals(personId, peopleResponse.id)
        Assert.assertEquals(eventId, peopleResponse.eventId)
        Assert.assertEquals(personName, peopleResponse.name)
        Assert.assertEquals(picture, peopleResponse.picture)
    }

    @Test
    fun `test people hydratation`() {
        val peopleResponse = PersonResponse(personId, eventId, personName, picture)
        Assert.assertEquals(personId, peopleResponse.id)
        Assert.assertEquals(eventId, peopleResponse.eventId)
        Assert.assertEquals(personName, peopleResponse.name)
        Assert.assertEquals(picture, peopleResponse.picture)
    }

    @Test
    fun `test people hydratation with null picture`() {
        val peopleResponse = PersonResponse(personId, eventId, personName)
        Assert.assertEquals(personId, peopleResponse.id)
        Assert.assertEquals(eventId, peopleResponse.eventId)
        Assert.assertEquals(personName, peopleResponse.name)
        Assert.assertNull(peopleResponse.picture)
    }
}