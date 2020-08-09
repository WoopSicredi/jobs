package br.com.nerdrapido.mvvmmockapiapp.data.model

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 *
 * Wrapper para entidades da camada data.
 */
sealed class DataWrapper<out T> {

    /**
     * Quando há sucesso na aquisição do dado.
     */
    data class Success<out T>(val value: T) : DataWrapper<T>()

    /**
     * Quando há um erro genérico na requisição.
     */
    data class GenericError(val error: Throwable) :
        DataWrapper<Nothing>()

    /**
     * Quando há erro de rede, ou seja, a requisição nem foi concluída com sucesso.
     */
    data class NetworkError(val error: Throwable) : DataWrapper<Nothing>()
}
