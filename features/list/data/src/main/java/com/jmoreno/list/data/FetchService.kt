package com.jmoreno.list.data

import com.jmoreno.list.data.models.FetchNetworkItem
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {
    @GET("/hiring.json")
    suspend fun listRepos(): Response<List<FetchNetworkItem>>
}