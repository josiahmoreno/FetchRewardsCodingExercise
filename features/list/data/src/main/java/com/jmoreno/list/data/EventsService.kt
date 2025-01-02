package com.jmoreno.list.data

import com.jmoreno.list.data.models.EventsNetworkItem
import retrofit2.Response
import retrofit2.http.GET

interface EventsService {
    @GET("/phunware-services/dev-interview-homework/master/feed.json")
    suspend fun listRepos(): Response<List<EventsNetworkItem>>
}