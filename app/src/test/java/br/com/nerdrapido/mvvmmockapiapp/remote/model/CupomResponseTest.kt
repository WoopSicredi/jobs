package br.com.nerdrapido.mvvmmockapiapp.remote.model

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.helpers.json.JsonMapper
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

    val id = "3"
    val eventId = "3"
    val discount = 17
    val cuponJson = "{\"id\":\"$id\",\"eventId\":\"$eventId\",\"discount\":$discount}"

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
        Assert.assertEquals(id, cupomResponse.id)
        Assert.assertEquals(eventId, cupomResponse.eventId)
        Assert.assertEquals(discount, cupomResponse.discount)
    }

    @Test
    fun `test cupom hydratation`() {
        val cupomResponse = CupomResponse(id, eventId, discount)
        Assert.assertEquals(id, cupomResponse.id)
        Assert.assertEquals(eventId, cupomResponse.eventId)
        Assert.assertEquals(discount, cupomResponse.discount)
    }
}
