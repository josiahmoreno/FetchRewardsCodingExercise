package com.jmoreno.list.data.repo

import com.jmoreno.list.data.EventsRepository
import com.jmoreno.list.data.models.EventsDto
import com.jmoreno.list.data.models.EventsNetworkItem

class EventsRepositoryImpl(private val remoteDataSource: FetchRemoteDataSource) :
    EventsRepository {
    override suspend fun fetchEventsItems(): Result<List<EventsDto>> {
        val result = remoteDataSource.fetchListData()
        return result.map { list ->
            list.map { it.mapToDto()}
        }
    }
}

private fun EventsNetworkItem.mapToDto(): EventsDto {
    return EventsDto(
        id = id,
        description = description,
        title = title,
        timestamp = timestamp,
        image = image,
        phone = phone,
        locationline1 = locationline1,
        locationline2 = locationline2
    )
}
