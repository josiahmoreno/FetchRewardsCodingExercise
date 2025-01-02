package com.jmoreno.list.domain

import com.jmoreno.list.data.models.EventsDto
import com.jmoreno.list.data.FetchListRepository

class FetchListUseCase(private val fetchListRepository: FetchListRepository) {

    suspend operator fun invoke(filterNullsOrBlank: Boolean): Result<List<EventsDomainModel>> {
        return fetchListRepository.fetchListItems().map { it.mapToDomain(filterNullsOrBlank) }
    }

    private fun List<EventsDto>.mapToDomain(filterNullsOrBlank: Boolean): List<EventsDomainModel> {
        return map { 
            it.mapToDomainModel()
        }
        return listOf()
        val groupMap = HashMap<Int, MutableList<ItemDomainModel>>()
//        forEach { dto ->
//
//            val mutableList = groupMap.getOrPut(dto.listId, { mutableListOf() })
//            val name = dto.name
//            //  Do not add null or blank. There could be a filtering operation here instead,
//            //  A dependency like val filteringOperation: FilteringOperation
//            //
//
//            //
//            if(!filterNullsOrBlank || !name.isNullOrBlank()) {
//                mutableList.add(ItemDomainModel(dto.id,name))
//            }
//        }
//        val mapped = groupMap.map { group ->
//            //  Sort grouped items by id
//            //  There could be a sorting operation here instead,
//            val lengthComparator = compareBy<ItemDomainModel> { it.name?.length }
//            //println("length = "+ group.value.sortedWith(lengthComparator)) // [b, a, aa, bb]
//
//            val lengthThenString = lengthComparator.thenBy { it.name }
//            println("then" + group.value.sortedWith(lengthThenString)) // [a, b, aa, bb]
//            //group.value.sortBy { it.name }
//            //group.value.sortedWith(lengthThenString)
//            val sortedItems = group.value.sortedWith(compareBy<ItemDomainModel> { it.name?.length }.thenBy { it.name })
//            println("extra "+ sortedItems)
//            GroupDomainModel(
//                groupId = group.key,
//                data = sortedItems
//            )
//        }
        //return mapped.sortedBy { it.groupId }
    }
}

private fun EventsDto.mapToDomainModel() : EventsDomainModel{
    return EventsDomainModel(
        id = id,
        imgSrc = image,
        date = date,
        title = title,
        locationline1 = locationline1,
        locationline2 = locationline2,
        description = description
    )
}






