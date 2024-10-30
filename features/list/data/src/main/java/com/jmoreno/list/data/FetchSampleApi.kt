package com.jmoreno.list.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchSampleApi : IFetchApi {
    override suspend fun fetchJson(): Result<List<FetchNetworkItem>> {
        return Result.success(
                listOf(
                    FetchNetworkItem(id = 755, listId =  2, name=  ""),
                    FetchNetworkItem(id = 203, listId =  2, name=  ""),
                    FetchNetworkItem(id = 684, listId =  1, name=  "Item 684"),
                    FetchNetworkItem(id = 276, listId =  1, name=  "Item 276"),
                )
            )
    }
}
