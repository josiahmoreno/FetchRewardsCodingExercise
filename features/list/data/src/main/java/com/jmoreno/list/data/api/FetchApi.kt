package com.jmoreno.list.data.api

import com.jmoreno.list.data.FetchService
import com.jmoreno.list.data.IFetchApi
import com.jmoreno.list.data.models.FetchNetworkItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class FetchApi(
    private val fetchService: FetchService,
    private val dispatcher: CoroutineDispatcher
) : IFetchApi {
    override suspend fun fetchJson(): Result<List<FetchNetworkItem>> {
        return withContext(dispatcher) {

            val result = runCatching {
                fetchService.listRepos()
            }
            result.map {
                it.body()!!
            }
        }
    }
}


