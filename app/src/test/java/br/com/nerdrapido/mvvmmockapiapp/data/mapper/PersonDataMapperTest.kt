package br.com.nerdrapido.mvvmmockapiapp.data.mapper

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.person.PersonDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.person.PersonDataMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.remote.model.PersonResponse
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.personName
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.personEventId
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
class PersonDataMapperTest : KoinTest {

    private val personDataMapper : PersonDataMapper = PersonDataMapperImpl()

    @Test
    fun `test people map from remote to data`() {
        val peopleResponse = PersonResponse(personId, personEventId, personName, picture)
        val peopleData = personDataMapper.mapRemoteToData(peopleResponse)
        Assert.assertEquals(personId, peopleData.id)
        Assert.assertEquals(personEventId, peopleData.eventId)
        Assert.assertEquals(personName, peopleData.name)
        Assert.assertEquals(picture, peopleData.picture)
    }

    @Test
    fun `test people map from remote to data with null picture`() {
        val peopleResponse = PersonResponse(personId, personEventId, personName)
        val peopleData = personDataMapper.mapRemoteToData(peopleResponse)
        Assert.assertEquals(personId, peopleData.id)
        Assert.assertEquals(personEventId, peopleData.eventId)
        Assert.assertEquals(personName, peopleData.name)
        Assert.assertNull(peopleData.picture)
    }
}