package com.jmoreno.list.data.repo

import com.jmoreno.list.data.FetchListRepository
import com.jmoreno.list.data.models.FetchRewardsDto

class FetchListRepositoryImpl(private val remoteDataSource: FetchRemoteDataSource) :
    FetchListRepository {
    override suspend fun fetchListItems(): Result<List<FetchRewardsDto>> {
        val result = remoteDataSource.fetchListData()
        return result.map { list ->
            list.map { FetchRewardsDto(id = it.id, listId = it.listId, name = it.name) }
        }
    }
}