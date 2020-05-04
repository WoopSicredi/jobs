package com.example.appteste.model

import java.io.Serializable

data class CheckIn(
    val eventId: String,
    val name: String,
    val email: String
): Serializable