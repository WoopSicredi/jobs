package br.com.nerdrapido.mvvmmockapiapp.remote.model

/**
 * Created By FELIPE GUSBERTI @ 11/08/2020
 */
data class CheckInPost(
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
    val email: String
)