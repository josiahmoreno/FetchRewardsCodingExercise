package com.jmoreno.list.data.repo

import com.jmoreno.list.data.IEventsApi
import com.jmoreno.list.data.models.EventsNetworkItem

class FetchRemoteDataSource(private val fetchApi: IEventsApi) {
    suspend fun fetchListData(): Result<List<EventsNetworkItem>> {
        //  Used 'return Result.failure(Exception(""))' to try out error handing
        //
        return fetchApi.fetchJson()
    }
}
