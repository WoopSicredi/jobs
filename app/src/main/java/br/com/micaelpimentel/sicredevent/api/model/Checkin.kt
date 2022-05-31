package br.com.micaelpimentel.sicredevent.api.model

import com.google.gson.annotations.SerializedName

data class Checkin (
    @SerializedName("eventId")
    val eventId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
)