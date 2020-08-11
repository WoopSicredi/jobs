package br.com.nerdrapido.mvvmmockapiapp.remote.model

import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.cupomEventId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.cuponJson
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.discount
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.eventId
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class CupomResponseTest : KoinTest {

    @Test
    fun `test cupom hydratation from json`() {
        val cupomResponse = Gson().fromJson(cuponJson, CupomResponse::class.java)
        Assert.assertEquals(cupomEventId, cupomResponse.id)
        Assert.assertEquals(eventId, cupomResponse.eventId)
        Assert.assertEquals(discount, cupomResponse.discount)
    }

    @Test
    fun `test cupom hydratation`() {
        val cupomResponse = CupomResponse(cupomEventId, eventId, discount)
        Assert.assertEquals(cupomEventId, cupomResponse.id)
        Assert.assertEquals(eventId, cupomResponse.eventId)
        Assert.assertEquals(discount, cupomResponse.discount)
    }
}
