package br.com.nerdrapido.mvvmmockapiapp.data.mapper

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.checkIn.CheckInDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.checkIn.CheckInDataMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.remote.model.CheckInPost
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.checkInEmail
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.checkInEventId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.checkInName
import org.junit.Assert
import org.junit.Test

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class CheckInDataMapperTest {

    private val checkInDataMapper: CheckInDataMapper = CheckInDataMapperImpl()

    @Test
    fun `test map check in from remote to data `() {
        val checkInPost = CheckInPost(checkInEventId, checkInName, checkInEmail)
        val data = checkInDataMapper.mapRemoteToData(checkInPost)
        Assert.assertEquals(checkInEventId, data.eventId)
        Assert.assertEquals(checkInName, data.name)
        Assert.assertEquals(checkInEmail, data.email)
    }

    @Test
    fun `test map check in from remote list to data list `() {
        val checkInPost = listOf(CheckInPost(checkInEventId, checkInName, checkInEmail))
        val data = checkInDataMapper.mapRemoteToDataList(checkInPost)[0]
        Assert.assertEquals(checkInEventId, data.eventId)
        Assert.assertEquals(checkInName, data.name)
        Assert.assertEquals(checkInEmail, data.email)
    }

    @Test
    fun `test map check in to remote from data`() {
        val checkInData = CheckInData(checkInEventId, checkInName, checkInEmail)
        val checkInPost = checkInDataMapper.mapDataToRemote(checkInData)
        Assert.assertEquals(checkInEventId, checkInPost.eventId)
        Assert.assertEquals(checkInName, checkInPost.name)
        Assert.assertEquals(checkInEmail, checkInPost.email)
    }

    @Test
    fun `test map check in to remote list from data list`() {
        val checkInData = listOf(CheckInData(checkInEventId, checkInName, checkInEmail))
        val checkInPost = checkInDataMapper.mapDataToRemoteList(checkInData)[0]
        Assert.assertEquals(checkInEventId, checkInPost.eventId)
        Assert.assertEquals(checkInName, checkInPost.name)
        Assert.assertEquals(checkInEmail, checkInPost.email)
    }
}