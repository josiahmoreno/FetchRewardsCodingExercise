package com.jmoreno.list.data.repo

import android.util.Log
import com.jmoreno.list.data.IFetchApi
import com.jmoreno.list.data.models.FetchNetworkItem

class FetchRemoteDataSource(private val fetchApi: IFetchApi) {
    suspend fun fetchListData(): Result<List<FetchNetworkItem>> {
        //return Result.failure(Exception(""))
        return fetchApi.fetchJson()
    }
}
