package com.jmoreno.list.data.api

import com.jmoreno.list.data.EventsService
import com.jmoreno.list.data.IEventsApi
import com.jmoreno.list.data.models.EventsNetworkItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class EventsApi(
    private val fetchService: EventsService,
    private val dispatcher: CoroutineDispatcher
) : IEventsApi {
    override suspend fun fetchJson(): Result<List<EventsNetworkItem>> {
        return withContext(dispatcher) {

            val result = runCatching {
                fetchService.listRepos()
            }
            result.mapCatching {
               // throw Exception()
                it.body()!!
            }
        }
    }
}


