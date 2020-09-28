package br.com.angelorobson.templatemvi.model.domains

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        val people: List<People>,
        val date: Long,
        val description: String,
        val image: String,
        val longitude: String,
        val latitude: String,
        val price: Float,
        val title: String,
        val id: String) : Parcelable

@Parcelize
data class People(
        val picture: String,
        val name: String,
        val eventId: String,
        val id: String) : Parcelable