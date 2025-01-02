package com.jmoreno.list.data

import com.jmoreno.list.data.models.EventsDto

interface FetchListRepository {
    suspend fun fetchListItems(): Result<List<EventsDto>>
}

