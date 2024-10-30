package com.jmoreno.list.data

class FetchRemoteDataSource(private val fetchApi: IFetchApi) {
    suspend fun fetchListData(): Result<List<FetchNetworkItem>> {
        //return Result.failure(Exception("Test"))
        return fetchApi.fetchJson()
    }

}
