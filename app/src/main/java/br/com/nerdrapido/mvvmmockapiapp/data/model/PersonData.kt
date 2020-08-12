package br.com.nerdrapido.mvvmmockapiapp.data.model

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
data class PersonData(
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