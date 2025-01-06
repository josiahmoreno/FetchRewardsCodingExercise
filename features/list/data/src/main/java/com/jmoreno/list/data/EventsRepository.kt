package com.jmoreno.list.data

import com.jmoreno.list.data.models.EventsDto

interface EventsRepository {
    suspend fun fetchEventsItems(): Result<List<EventsDto>>
}

