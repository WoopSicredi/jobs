package br.com.nerdrapido.mvvmmockapiapp.presentation.model

/**
 * Created By FELIPE GUSBERTI @ 10/08/2020
 */
data class Event (
    /**
     * Considerado parâmetro obrigatório.
     */
    val date: String,
    /**
     * Considerado parâmetro obrigatório.
     */
    val description: String,
    /**
     * Considerado parâmetro obrigatório.
     */
    val image: String,
    /**
     * Considerado parâmetro obrigatório.
     */
    val longitude: Double,
    /**
     * Considerado parâmetro obrigatório.
     */
    val latitude: Double,
    /**
     * Considerado parâmetro obrigatório.
     */
    val price: Float,
    /**
     * Considerado parâmetro obrigatório.
     */
    val title: String,
    /**
     * Considerado parâmetro obrigatório.
     */
    val id: String,

    val cupons: List<Cupom> = emptyList(),

    val people: List<Person> = emptyList()
)