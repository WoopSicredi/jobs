package br.com.nerdrapido.mvvmmockapiapp.presentation.model

/**
 * Created By FELIPE GUSBERTI @ 10/08/2020
 */
data class Cupom(
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
    val discount: Int
)