package br.com.angelorobson.templatemvi.model.builders

import br.com.angelorobson.templatemvi.model.domains.Event
import br.com.angelorobson.templatemvi.model.domains.People

class EventBuilder {

    data class Builder(
            private var people: List<People> = listOf(),
            private var date: Long = 1L,
            private var description: String = "",
            private var image: String = "",
            private var longitude: String = "",
            private var latitude: String = "",
            private var price: Float = 1F,
            private var title: String = "",
            private var id: String = ""
    ) {

        fun people(people: List<People>) =
                apply { this.people = people }

        fun date(date: Long) = apply { this.date = date }
        fun description(description: String) = apply { this.description = description }
        fun image(image: String) = apply { this.image = image }
        fun longitude(longitude: String) = apply { this.longitude = longitude }
        fun latitude(latitude: String) = apply { this.latitude = latitude }
        fun price(price: Float) = apply { this.price = price }
        fun title(title: String) = apply { this.title = title }
        fun id(id: String) = apply { this.id = id }


        fun oneEvent() = apply {
            people = listOf(PeopleBuilder.Builder().onePeople().build())
            date = 123454545
            description = "descripton"
            image = "https://image"
            longitude = "-154"
            latitude = "-555555"
            price = 18.0F
            title = "Evento ok"
            id = "1"
        }

        fun build() = Event(
                people = people,
                id = id,
                longitude = longitude,
                latitude = latitude,
                image = image,
                date = date,
                title = title,
                price = price,
                description = description
        )
    }
}