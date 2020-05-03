package com.example.appteste.model

import java.io.Serializable

data class Person(
    val id: String,
    val eventId: String,
    val name: String,
    val picture: String
): Serializable