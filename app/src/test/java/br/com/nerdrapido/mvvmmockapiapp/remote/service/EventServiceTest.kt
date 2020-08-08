package br.com.nerdrapido.mvvmmockapiapp.remote.service

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import br.com.nerdrapido.mvvmmockapiapp.di.MainModule
import br.com.nerdrapido.mvvmmockapiapp.remote.network.MockServiceInterceptorWithFile
import br.com.nerdrapido.mvvmmockapiapp.remote.network.NetworkController
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import java.net.HttpURLConnection

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class EventServiceTest : KoinTest {

    val date = 1534784400000L
    val description =
        "Atenção! Para nosso brique ser o mais organizado possível, leia as regras e cumpra-as:\n* Ao publicar seus livros, evite criar álbuns (não há necessidade de remetê-los a nenhum álbum);\n* A publicação deverá conter o valor desejado;\n* É preferível publicar uma foto do livro em questão a fim de mostrar o estado em que se encontra;\n* Respeite a ordem da fila;\n* Horário e local de encontro devem ser combinados inbox;\n* Caso não possa comparecer, avise seu comprador/vendedor previamente;\n* Caso seu comprador desista, comente o post com \"disponível\";\n* Não se esqueça de apagar a publicação se o livro já foi vendido, ou ao menos comente \"vendido\" para que as administradoras possam apagá-la;\n* Evite discussões e respeite o próximo;\n"
    val image = "http://www.fernaogaivota.com.br/documents/10179/1665610/feira-troca-de-livros.jpg"
    val longitude = -51.2148497
    val latitude = -30.037878
    val price = 19.99F
    val title = "Feira de Troca de Livros"
    val id = "3"

    private val context = ApplicationProvider.getApplicationContext<Application>()

    private val networkController: NetworkController by inject()

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
    fun `test getEventList success`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithFile(
                        "item_list_success.json",
                        HttpURLConnection.HTTP_OK
                    )
                }
            }
        )

        val service = networkController.retrofit.create(EventService::class.java)

        runBlocking {
            val response = service.getEventList()
            Assert.assertEquals(3, response.count())
            val itemResponse = response[2]
            Assert.assertEquals(date, itemResponse.date)
            Assert.assertEquals(description, itemResponse.description)
            Assert.assertEquals(image, itemResponse.image)
            Assert.assertEquals(longitude, itemResponse.longitude, 0.0000001)
            Assert.assertEquals(latitude, itemResponse.latitude, 0.0000001)
            Assert.assertEquals(price, itemResponse.price)
            Assert.assertEquals(title, itemResponse.title)
            Assert.assertEquals(id, itemResponse.id)
            Assert.assertEquals(1, itemResponse.cupons.count())
            Assert.assertEquals(1, itemResponse.people.count())
        }
    }

    @Test
    fun `test getEvent success`() {
        loadKoinModules(
            module {
                single<Interceptor>(override = true) {
                    MockServiceInterceptorWithFile(
                        "item_success.json",
                        HttpURLConnection.HTTP_OK
                    )
                }
            }
        )

        val service = networkController.retrofit.create(EventService::class.java)

        runBlocking {
            val itemResponse = service.getEvent("3")
            Assert.assertEquals(date, itemResponse.date)
            Assert.assertEquals(description, itemResponse.description)
            Assert.assertEquals(image, itemResponse.image)
            Assert.assertEquals(longitude, itemResponse.longitude, 0.0000001)
            Assert.assertEquals(latitude, itemResponse.latitude, 0.0000001)
            Assert.assertEquals(price, itemResponse.price)
            Assert.assertEquals(title, itemResponse.title)
            Assert.assertEquals(id, itemResponse.id)
            Assert.assertEquals(1, itemResponse.cupons.count())
            Assert.assertEquals(1, itemResponse.people.count())
        }
    }
}