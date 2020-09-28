package br.com.angelorobson.templatemvi.model.dtos.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckInRequestDto(
        val eventId: String,
        val name: String,
        val email: String
)