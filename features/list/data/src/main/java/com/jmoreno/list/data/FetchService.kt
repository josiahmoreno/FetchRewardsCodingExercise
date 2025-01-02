package com.jmoreno.list.data

import com.jmoreno.list.data.models.FeedNetworkItem
import retrofit2.Response
import retrofit2.http.GET

interface FetchService {
    @GET("/feed.json")
    suspend fun listRepos(): Response<List<FeedNetworkItem>>
}