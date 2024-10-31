package com.jmoreno.list.data.repo

import com.jmoreno.list.data.IFetchApi
import com.jmoreno.list.data.models.FetchNetworkItem

class FetchRemoteDataSource(private val fetchApi: IFetchApi) {
    suspend fun fetchListData(): Result<List<FetchNetworkItem>> {
        //  Used 'return Result.failure(Exception(""))' to try out error handing
        //
        return fetchApi.fetchJson()
    }
}
