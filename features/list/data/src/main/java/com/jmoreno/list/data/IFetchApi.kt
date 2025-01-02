package com.jmoreno.list.data

import com.jmoreno.list.data.models.FeedNetworkItem

interface IFetchApi {
    suspend fun fetchJson(): Result<List<FeedNetworkItem>>
}