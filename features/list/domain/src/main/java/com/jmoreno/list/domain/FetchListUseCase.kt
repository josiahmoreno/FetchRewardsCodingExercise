package com.jmoreno.list.domain

import com.jmoreno.list.data.FetchRewardsDto
import com.jmoreno.list.data.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchListUseCase(private val listRepository: ListRepository) {

    suspend operator fun invoke(): Flow<List<FetchRewardsDomainModel>> {
        return listRepository.fetchListItems().map {  it.mapToDomain()}
    }
}


private fun List<FetchRewardsDto>.mapToDomain(): List<FetchRewardsDomainModel> {
    return map { dto ->
        FetchRewardsDomainModel(
        id = dto.id,
        listId = dto.listId,
        name = dto.name
    ) }
}




