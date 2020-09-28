package br.com.angelorobson.templatemvi.model.builders

import br.com.angelorobson.templatemvi.model.domains.People

class PeopleBuilder {

    data class Builder(
            private var picture: String = "",
            private var name: String = "",
            private var eventId: String = "",
            private var id: String = ""
    ) {

        fun picture(picture: String) =
                apply { this.picture = picture }

        fun name(name: String) = apply { this.name = name }
        fun eventId(eventId: String) = apply { this.eventId = eventId }
        fun id(id: String) = apply { this.id = id }

        fun onePeople() = apply {
            picture = "imagem"
            name = "user 1"
            eventId = "1"
            id = "1"
        }

        fun build() = People(
                picture = picture,
                name = name,
                eventId = eventId,
                id = id
        )
    }



}