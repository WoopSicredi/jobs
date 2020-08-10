package br.com.nerdrapido.mvvmmockapiapp.presentation.exception

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 *
 * Exception chamada quando há erro na requisição.
 */
class ApiCallNetworkException(throwable: Throwable? = null) : Exception(throwable)