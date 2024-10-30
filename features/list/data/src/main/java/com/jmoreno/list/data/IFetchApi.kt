package com.jmoreno.list.data

import kotlinx.coroutines.flow.Flow

interface IFetchApi {
    suspend fun fetchJson(): Result<List<FetchNetworkItem>>
}