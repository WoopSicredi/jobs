package br.com.nerdrapido.mvvmmockapiapp.remote.model

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.helpers.json.JsonMapper
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.cupomEventId
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.cuponJson
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.discount
import br.com.nerdrapido.mvvmmockapiapp.remote.model.RemoteModelMock.eventId
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class CupomResponseTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val jsonMapper: JsonMapper by inject()

    @Before
    fun setUp() {
        startKoin {
            androidContext(context)
            modules(
                MainModule.module
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test cupom hydratation from json`() {
        val cupomResponse = jsonMapper.fromString(cuponJson, CupomResponse::class.java)
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
