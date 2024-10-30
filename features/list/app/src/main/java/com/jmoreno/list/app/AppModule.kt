package com.jmoreno.list.app

import com.jmoreno.list.data.FetchApi
import com.jmoreno.list.data.ListRepository
import com.jmoreno.list.data.ListRepositoryImpl
import com.jmoreno.list.ui.UiModule
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val AppModule = module {
    includes(UiModule)
    singleOf(::ListRepositoryImpl) { bind<ListRepository>() }
    singleOf(::FetchApi) { bind<FetchApi>() }
}