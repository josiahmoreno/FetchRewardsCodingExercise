package com.jmoreno.list.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET


class FetchApi(private val fetchService: FetchService, private val dispatcher: CoroutineDispatcher ) : IFetchApi {
    override suspend fun fetchJson(): Result<List<FetchNetworkItem>> {
        return withContext(dispatcher) {
            fetchService.listRepos().runCatching {

            }
            val response = fetchService.listRepos()
            if (response.isSuccessful) {
                 Result.success(response.body()!!)
            } else {
                val failure = HttpException(response)
                Result.failure(failure)
            }
        }
    }
}

interface FetchService {
    @GET("/hiring.json")
    suspend fun listRepos(): Response<List<FetchNetworkItem>>
}


data class FetchNetworkItem(
    val id: Int,
    val listId: Int,
    val name: String?,
)