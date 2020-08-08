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
import java.io.InputStream

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
@RunWith(RobolectricTestRunner::class)
class EventResponseTest : KoinTest {

    val date = 1534784400000L
    val description =
        "Atenção! Para nosso brique ser o mais organizado possível, leia as regras e cumpra-as:\n* Ao publicar seus livros, evite criar álbuns (não há necessidade de remetê-los a nenhum álbum);\n* A publicação deverá conter o valor desejado;\n* É preferível publicar uma foto do livro em questão a fim de mostrar o estado em que se encontra;\n* Respeite a ordem da fila;\n* Horário e local de encontro devem ser combinados inbox;\n* Caso não possa comparecer, avise seu comprador/vendedor previamente;\n* Caso seu comprador desista, comente o post com \"disponível\";\n* Não se esqueça de apagar a publicação se o livro já foi vendido, ou ao menos comente \"vendido\" para que as administradoras possam apagá-la;\n* Evite discussões e respeite o próximo;\n"
    val image = "http://www.fernaogaivota.com.br/documents/10179/1665610/feira-troca-de-livros.jpg"
    val longitude = -51.2148497
    val latitude = -30.037878
    val price = 19.99F
    val title = "Feira de Troca de Livros"
    val id = "3"
    val cuponJson : String

    init {
        val classloader = Thread.currentThread().contextClassLoader
        val inputStream: InputStream = classloader.getResourceAsStream("item_success.json")
        cuponJson = inputStream.bufferedReader().use { it.readText() }
    }

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
    fun `test item hydratation from json`() {
        val itemResponse = jsonMapper.fromString(cuponJson, EventResponse::class.java)
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
