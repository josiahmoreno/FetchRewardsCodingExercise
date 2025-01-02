package com.jmoreno.list.app

import com.jmoreno.list.data.repo.FetchRemoteDataSource
import com.jmoreno.list.data.IEventsApi
import com.jmoreno.list.data.FetchListRepository
import com.jmoreno.list.data.api.*
import com.jmoreno.list.data.repo.FetchListRepositoryImpl
import com.jmoreno.list.ui.UiModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AppModule = module {
    includes(UiModule)
    includes(NetworkModule)
    singleOf(::FetchRemoteDataSource) { bind<FetchRemoteDataSource>() }
    singleOf(::FetchListRepositoryImpl) { bind<FetchListRepository>() }
    singleOf(::EventsApi) { bind<IEventsApi>() }
    single<CoroutineDispatcher> { Dispatchers.IO }
}