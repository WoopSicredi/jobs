package br.com.angelorobson.templatemvi.utils

object FileUtils {

    @JvmStatic
    fun getJson(path: String): String {
        val url = this.javaClass.classLoader?.getResource(path)
        return String(url?.readBytes()!!)
    }
}