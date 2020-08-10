package br.com.nerdrapido.mvvmmockapiapp.presentation.model

import br.com.nerdrapido.mvvmmockapiapp.remote.model.CupomResponse
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.cupomEventId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.cupumId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.discount
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.eventId
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner

/**
 * Created By FELIPE GUSBERTI @ 10/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class CupomTest : KoinTest {

    @Test
    fun `test CupomTest construction`() {

        val personTest = Cupom(cupumId, cupomEventId, discount)
        Assert.assertEquals(cupumId, personTest.id)
        Assert.assertEquals(eventId, personTest.eventId)
        Assert.assertEquals(discount, personTest.discount)



    }

}