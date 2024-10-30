package com.jmoreno.list.data

import kotlinx.coroutines.flow.Flow

class ListRepositoryImpl(private val fetchApi: FetchApi) : ListRepository {
    override suspend fun fetchListItems(): Flow<List<FetchRewardsDto>> {
        return fetchApi.fetchJson()
    }

}