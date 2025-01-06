package com.jmoreno.list.domain

import com.jmoreno.list.data.EventsRepository
import com.jmoreno.list.data.models.EventsDto

class FetchEventsUseCase(private val eventsRepository: EventsRepository) {

    suspend operator fun invoke(): Result<List<EventsDomainModel>> {
        return eventsRepository.fetchEventsItems().map { it.mapToDomain() }
    }

    private fun List<EventsDto>.mapToDomain(): List<EventsDomainModel> {
        return map {
            it.mapToDomainModel()
        }
    }
}

private fun EventsDto.mapToDomainModel(): EventsDomainModel {
    return EventsDomainModel(
        id = id,
        imgSrc = image,
        timeStamp = timestamp,
        title = title,
        phone = phone,
        locationline1 = locationline1,
        locationline2 = locationline2,
        description = description
    )
}






