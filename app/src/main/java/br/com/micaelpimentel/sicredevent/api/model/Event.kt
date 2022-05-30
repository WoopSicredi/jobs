package br.com.micaelpimentel.sicredevent.api.model

data class Event(
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Float,
    val latitude: Float,
    val price: Float,
    val title: String,
    val id: String,
    val people: List<People>,
)

data class People(
    val name: String,
    val email: String,
)