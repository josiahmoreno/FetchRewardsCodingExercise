package com.jmoreno.list.domain

import com.jmoreno.list.data.models.FetchRewardsDto
import com.jmoreno.list.data.FetchListRepository

class FetchListUseCase(private val fetchListRepository: FetchListRepository) {

    suspend operator fun invoke(): Result<List<GroupDomainModel>> {
        return fetchListRepository.fetchListItems().map { it.mapToDomain() }
    }

    private fun List<FetchRewardsDto>.mapToDomain(): List<GroupDomainModel> {
        val groupMap = HashMap<Int, MutableList<ItemDomainModel>>()
        forEach { dto ->

            val mutableList = groupMap.getOrPut(dto.listId, { mutableListOf() })
            val name = dto.name
            //  Do not add null or blank. There could be a filtering operation here instead,
            //  A dependency like val filteringOperation: FilteringOperation
            //
            if(!name.isNullOrBlank()) {
                mutableList.add(ItemDomainModel(dto.id,name))
            }
        }
        val mapped = groupMap.map { group ->
            //  Sort grouped items by id
            //  There could be a sorting operation here instead,
            group.value.sortBy { it.name }
            GroupDomainModel(
                groupId = group.key,
                data = group.value
            )
        }
        return mapped.sortedBy { it.groupId }
    }
}






