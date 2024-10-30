package com.jmoreno.list.app

import com.jmoreno.list.data.FetchApi
import com.jmoreno.list.data.FetchRemoteDataSource
import com.jmoreno.list.data.IFetchApi
import com.jmoreno.list.data.FetchListRepository
import com.jmoreno.list.data.FetchListRepositoryImpl
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
    singleOf(::FetchApi) { bind<IFetchApi>() }
    single<CoroutineDispatcher> { Dispatchers.IO }
}