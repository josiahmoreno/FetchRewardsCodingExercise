package com.jmoreno.list.ui

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val UiModule = module {
    viewModelOf(::ListViewModel)
}