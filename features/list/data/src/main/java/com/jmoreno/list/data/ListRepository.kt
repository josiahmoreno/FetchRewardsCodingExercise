package com.jmoreno.list.data

import kotlinx.coroutines.flow.Flow

interface ListRepository {
    suspend fun fetchListItems(): Flow<List<String>>
}

