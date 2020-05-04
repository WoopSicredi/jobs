package com.example.appteste.model

import java.io.Serializable

data class Event(
    val people : ArrayList<Person>,
    val date: Long,
    val description: String = "",
    val image: String = "",
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String,
    val cupons: ArrayList<Coupon>
): Serializable