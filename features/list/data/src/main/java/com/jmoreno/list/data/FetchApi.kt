package com.jmoreno.list.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchApi {
    fun fetchJson(): Flow<List<String>> {
        return flow {
            emit(
                listOf(
                    "id: 755, listId: 2, name: ",
                    "{\"id\": 203, \"listId\": 2, \"name\": \"\"}",
                    "{\"id\": 684, \"listId\": 1, \"name\": \"Item 684\"}",
                    "{\"id\": 276, \"listId\": 1, \"name\": \"Item 276\"}"
                )
            )
        }
    }

}
