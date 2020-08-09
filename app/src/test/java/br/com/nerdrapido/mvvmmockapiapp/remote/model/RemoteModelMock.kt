package br.com.nerdrapido.mvvmmockapiapp.remote.model

import java.io.InputStream

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
object RemoteModelMock {


    val date = 1534784400000L
    val description =
        "Atenção! Para nosso brique ser o mais organizado possível, leia as regras e cumpra-as:\n* Ao publicar seus livros, evite criar álbuns (não há necessidade de remetê-los a nenhum álbum);\n* A publicação deverá conter o valor desejado;\n* É preferível publicar uma foto do livro em questão a fim de mostrar o estado em que se encontra;\n* Respeite a ordem da fila;\n* Horário e local de encontro devem ser combinados inbox;\n* Caso não possa comparecer, avise seu comprador/vendedor previamente;\n* Caso seu comprador desista, comente o post com \"disponível\";\n* Não se esqueça de apagar a publicação se o livro já foi vendido, ou ao menos comente \"vendido\" para que as administradoras possam apagá-la;\n* Evite discussões e respeite o próximo;\n"
    val image = "http://www.fernaogaivota.com.br/documents/10179/1665610/feira-troca-de-livros.jpg"
    val longitude = -51.2148497
    val latitude = -30.037878
    val price = 19.99F
    val title = "Feira de Troca de Livros"
    val eventId = "3"
    val eventJson : String

    val personId = "3"
    val personEventId = "3"
    val name = "name 3"
    val picture = "picture 3"
    val personJson = "{\"id\":\"$personId\",\"eventId\":\"$personEventId\",\"name\":\"$name\",\"picture\":\"$picture\"}"

    val cupumId = "3"
    val cupomEventId = "3"
    val discount = 17
    val cuponJson = "{\"id\":\"$cupumId\",\"eventId\":\"$eventId\",\"discount\":$discount}"

    init {
        val classloader = Thread.currentThread().contextClassLoader
        val inputStream: InputStream = classloader.getResourceAsStream("item_success.json")
        eventJson = inputStream.bufferedReader().use { it.readText() }
    }
}