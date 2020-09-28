package br.com.angelorobson.templatemvi.model.repositories

import br.com.angelorobson.templatemvi.model.domains.CheckIn
import br.com.angelorobson.templatemvi.model.domains.Event
import br.com.angelorobson.templatemvi.model.domains.People
import br.com.angelorobson.templatemvi.model.dtos.request.CheckInRequestDto
import br.com.angelorobson.templatemvi.model.dtos.response.EventResponseDto
import br.com.angelorobson.templatemvi.model.dtos.response.PeopleDto
import br.com.angelorobson.templatemvi.model.services.EventService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class EventRepository @Inject constructor(
        private val eventService: EventService
) {
    fun getAll(): Observable<List<Event>> {
        return eventService.getAll()
                .map { response ->
                    response.map {
                        mapDtoToDomain(it)
                    }
                }
    }

    fun getEvent(id: String): Observable<Event> {
        return eventService.getEvent(id)
                .map {
                    mapDtoToDomain(it)
                }
    }

    fun checkIn(checkIn: CheckIn): Completable {
        return Single.fromCallable { mapToDto(checkIn) }.flatMapCompletable { eventService.checkIn(it) }
    }
}

fun mapDtoToDomain(dto: EventResponseDto): Event {
    return Event(
            people = dto.people.map { mapDtoToDomain(it) },
            description = dto.description,
            id = dto.id,
            price = dto.price,
            title = dto.title,
            date = dto.date,
            image = dto.image,
            latitude = dto.latitude,
            longitude = dto.longitude
    )
}

fun mapDtoToDomain(dto: PeopleDto): People {
    return People(
            picture = dto.picture,
            id = dto.id,
            name = dto.name,
            eventId = dto.eventId
    )
}

private fun mapToDto(checkIn: CheckIn): CheckInRequestDto {
    return CheckInRequestDto(
            eventId = checkIn.eventId,
            name = checkIn.name,
            email = checkIn.email
    )
}