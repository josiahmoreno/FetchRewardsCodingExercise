package com.jmoreno.list.data

import com.jmoreno.list.data.models.EventsNetworkItem

interface IEventsApi {
    suspend fun fetchJson(): Result<List<EventsNetworkItem>>
}