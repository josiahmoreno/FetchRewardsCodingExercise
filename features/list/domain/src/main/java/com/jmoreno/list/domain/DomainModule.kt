package com.jmoreno.list.domain

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val DomainModule = module {
    factoryOf(::FetchListUseCase)
}