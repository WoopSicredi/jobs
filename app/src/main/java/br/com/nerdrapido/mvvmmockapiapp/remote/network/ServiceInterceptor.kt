package br.com.nerdrapido.mvvmmockapiapp.remote.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
class ServiceInterceptor : Interceptor {

    /**
     * Intercepta o request para fazer inserções padrão de query ou header.
     * Nenhuma adição está implementada no mnomento, mas a classe está aqui para ser usada
     * futuramente.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url = originalHttpUrl.newBuilder().build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}