package com.example.appteste.model

import java.io.Serializable

data class EventList(val name: String = "", val list: ArrayList<Event> = arrayListOf()) : Serializable