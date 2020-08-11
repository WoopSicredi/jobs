package br.com.nerdrapido.mvvmmockapiapp.data.mapper

import br.com.nerdrapido.mvvmmockapiapp.data.mapper.cupom.CupomDataMapper
import br.com.nerdrapido.mvvmmockapiapp.data.mapper.cupom.CupomDataMapperImpl
import br.com.nerdrapido.mvvmmockapiapp.remote.model.CupomResponse
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.cupomEventId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.cupumId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.discount
import org.junit.Assert
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class CupomDataMapperTest : KoinTest {

    private val cupomDataMapper: CupomDataMapper = CupomDataMapperImpl()

    @Test
    fun `test map cupom from remote to data`() {
        val cupomResponse = CupomResponse(cupumId, cupomEventId, discount)
        val cupomData = cupomDataMapper.mapRemoteToData(cupomResponse)
        Assert.assertEquals(cupumId, cupomData.id)
        Assert.assertEquals(cupomEventId, cupomData.eventId)
        Assert.assertEquals(discount, cupomData.discount)
    }
}