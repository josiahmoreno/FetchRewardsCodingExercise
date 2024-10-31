package com.jmoreno.list.data

import com.jmoreno.list.data.models.FetchRewardsDto

interface FetchListRepository {
    suspend fun fetchListItems(): Result<List<FetchRewardsDto>>
}

