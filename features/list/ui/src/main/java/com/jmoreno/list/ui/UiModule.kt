package com.jmoreno.list.ui

import com.jmoreno.list.domain.DomainModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val UiModule = module {
    includes(DomainModule)
    viewModelOf(::ListViewModel)
}