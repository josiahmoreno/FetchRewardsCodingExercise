package com.jmoreno.list.domain

import com.jmoreno.list.data.FetchRewardsDto
import com.jmoreno.list.data.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchListUseCase(private val listRepository: ListRepository) {

    suspend operator fun invoke(): Flow<List<FetchRewardsDomainModel>> {
        return listRepository.fetchListItems().map { it.mapToDomain() }
    }
}


private fun List<FetchRewardsDto>.mapToDomain(): List<FetchRewardsDomainModel> {
    val groupMap = HashMap<Int, MutableList<FetchRewardsDto>>()
    forEach { dto ->
        val mutableList = groupMap.getOrPut(dto.listId, { mutableListOf() })
        mutableList.add(dto)
    }
    val mapped = groupMap.map { group ->
        FetchRewardsDomainModel(
            groupId = group.key,
            data = group.value.map { dto ->
                ItemDomainEntity(dto.id, dto.name)
            }
        )
    }
    return mapped.sortedBy { it.groupId }
}




