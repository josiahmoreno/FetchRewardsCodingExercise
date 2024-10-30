package com.jmoreno.list.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchApi {
    fun fetchJson(): Flow<List<FetchRewardsDto>> {
        return flow {
            emit(
                listOf(
                    FetchRewardsDto(id = 755, listId =  2, name=  ""),
                    FetchRewardsDto(id = 203, listId =  2, name=  ""),
                    FetchRewardsDto(id = 684, listId =  1, name=  "Item 684"),
                    FetchRewardsDto(id = 276, listId =  1, name=  "Item 276"),
                )
            )
        }
    }

}
