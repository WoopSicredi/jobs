package br.com.angelorobson.templatemvi.model.dtos.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventResponseDto(
        val people: List<PeopleDto>,
        val date: Long,
        val description: String,
        val image: String,
        val longitude: String,
        val latitude: String,
        val price: Float,
        val title: String,
        val id: String)

@JsonClass(generateAdapter = true)
data class PeopleDto(
        val picture: String,
        val name: String,
        val eventId: String,
        val id: String
)
