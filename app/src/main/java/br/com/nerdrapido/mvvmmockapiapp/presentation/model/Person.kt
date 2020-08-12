package br.com.nerdrapido.mvvmmockapiapp.presentation.model

/**
 * Created By FELIPE GUSBERTI @ 10/08/2020
 */
data class Person(
    /**
     * Considerado parâmetro obrigatório.
     */
    val id: String,
    /**
     * Considerado parâmetro obrigatório.
     */
    val eventId: String,
    /**
     * Considerado parâmetro obrigatório.
     */
    val name: String,
    /**
     * Considerado parâmetro obrigatório.
     */
    val picture: String? = null
)