package com.jmoreno.list.data

interface FetchListRepository {
    suspend fun fetchListItems(): Result<List<FetchRewardsDto>>
}

