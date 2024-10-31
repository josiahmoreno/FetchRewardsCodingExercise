package com.jmoreno.list.data

import com.jmoreno.list.data.models.FetchNetworkItem

interface IFetchApi {
    suspend fun fetchJson(): Result<List<FetchNetworkItem>>
}