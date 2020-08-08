package br.com.nerdrapido.mvvmmockapiapp.remote.model

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
data class CupomResponse(
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
