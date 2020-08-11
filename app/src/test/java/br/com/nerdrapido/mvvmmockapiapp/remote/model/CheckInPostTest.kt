package br.com.nerdrapido.mvvmmockapiapp.remote.model

import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.checkInEmail
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.checkInEventId
import br.com.nerdrapido.mvvmmockapiapp.testShared.RemoteModelMock.checkInName
import org.junit.Assert
import org.junit.Test

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
class CheckInPostTest {

    @Test
    fun `test cupom hydratation`() {
        val checkin = CheckInPost(
            checkInEventId,
            checkInName,
            checkInEmail
        )
        Assert.assertEquals(checkInEventId, checkin.eventId)
        Assert.assertEquals(checkInName, checkin.name)
        Assert.assertEquals(checkInEmail, checkin.email)
    }

}