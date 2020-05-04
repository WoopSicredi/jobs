package com.example.appteste.model

import java.io.Serializable

data class Coupon(
    val id: String,
    val eventId: String,
    val dicount: Int
): Serializable