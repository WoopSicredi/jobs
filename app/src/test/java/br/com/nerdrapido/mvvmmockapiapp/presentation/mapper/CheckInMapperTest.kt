package br.com.nerdrapido.mvvmmockapiapp.presentation.mapper

import br.com.nerdrapido.mvvmmockapiapp.data.model.CheckInData
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.checkIn.CheckInMapper
import br.com.nerdrapido.mvvmmockapiapp.presentation.mapper.checkIn.CheckInMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.presentation.model.CheckIn
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock
import org.junit.Assert
import org.junit.Test

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class CheckInMapperTest {

    private val checkInMapper: CheckInMapper = CheckInMapperImpl()

    @Test
    fun `test map check in from data to model `() {
        val checkInData = CheckInData(RemoteModelMock.checkInEventId, RemoteModelMock.checkInName, RemoteModelMock.checkInEmail)
        val model = checkInMapper.mapDataToModel(checkInData)
        Assert.assertEquals(RemoteModelMock.checkInEventId, model.eventId)
        Assert.assertEquals(RemoteModelMock.checkInName, model.name)
        Assert.assertEquals(RemoteModelMock.checkInEmail, model.email)
    }

    @Test
    fun `test map check in from data list to data model `() {
        val checkInData = listOf(CheckInData(RemoteModelMock.checkInEventId, RemoteModelMock.checkInName, RemoteModelMock.checkInEmail))
        val model = checkInMapper.mapDataToModelList(checkInData)[0]
        Assert.assertEquals(RemoteModelMock.checkInEventId, model.eventId)
        Assert.assertEquals(RemoteModelMock.checkInName, model.name)
        Assert.assertEquals(RemoteModelMock.checkInEmail, model.email)
    }

    @Test
    fun `test map check in from model to data`() {
        val model = CheckIn(RemoteModelMock.checkInEventId, RemoteModelMock.checkInName, RemoteModelMock.checkInEmail)
        val data = checkInMapper.mapModelToData(model)
        Assert.assertEquals(RemoteModelMock.checkInEventId, data.eventId)
        Assert.assertEquals(RemoteModelMock.checkInName, data.name)
        Assert.assertEquals(RemoteModelMock.checkInEmail, data.email)
    }

    @Test
    fun `test map check in from model list to data list`() {
        val model = listOf(CheckIn(RemoteModelMock.checkInEventId, RemoteModelMock.checkInName, RemoteModelMock.checkInEmail))
        val data = checkInMapper.mapModelToDataList(model)[0]
        Assert.assertEquals(RemoteModelMock.checkInEventId, data.eventId)
        Assert.assertEquals(RemoteModelMock.checkInName, data.name)
        Assert.assertEquals(RemoteModelMock.checkInEmail, data.email)
    }

}