package br.com.nerdrapido.mvvmmockapiapp.presentation.exception

/**
 * Created By FELIPE GUSBERTI @ 09/08/2020
 *
 * Exception chamada quando há erro não mapeado. Neste caso deve-se verificar o conteúdo de
 * throwable
 */
class NotMappedException(throwable: Throwable? = null) : Exception(throwable)