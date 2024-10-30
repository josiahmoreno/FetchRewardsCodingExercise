package com.jmoreno.list.domain

import com.jmoreno.list.data.FetchRewardsDto
import com.jmoreno.list.data.FetchListRepository

class FetchListUseCase(private val fetchListRepository: FetchListRepository) {

    suspend operator fun invoke(): Result<List<FetchRewardsDomainModel>> {
        return fetchListRepository.fetchListItems().map { it.mapToDomain() }
    }
}


private fun List<FetchRewardsDto>.mapToDomain(): List<FetchRewardsDomainModel> {

    val groupMap = HashMap<Int, MutableList<ItemDomainEntity>>()
//    val grouped: Map<Int, List<FetchRewardsDto>> = groupBy { dto ->
//        dto.listId
//    }

    forEach { dto ->

        val mutableList = groupMap.getOrPut(dto.listId, { mutableListOf() })
        val name = dto.name
        //  Do not add null or blank. There could be a filtering operation here instead,
        //  A dependency like val filteringOperation: FilteringOperation
        //
        if(!name.isNullOrBlank()) {
            mutableList.add(ItemDomainEntity(dto.id,name))
        }
    }
    val mapped = groupMap.map { group ->
        //  Sort grouped items by id
        //  There could be a sorting operation here instead,
        group.value.sortBy { it.name }
        FetchRewardsDomainModel(
            groupId = group.key,
            data = group.value
        )
    }
    return mapped.sortedBy { it.groupId }
}




