package br.com.nerdrapido.mvvmmockapiapp.data.model

/**
 * Created By FELIPE GUSBERTI @ 08/08/2020
 */
data class EventData(
    /**
     * Considerado parâmetro obrigatório.
     */
    val date: Long,
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

    val cupons: List<CupomData> = emptyList(),

    val people: List<PersonData> = emptyList()
)