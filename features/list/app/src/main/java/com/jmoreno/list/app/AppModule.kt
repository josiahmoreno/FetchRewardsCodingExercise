package com.jmoreno.list.app

import com.jmoreno.list.ui.UiModule
import org.koin.dsl.module

val AppModule = module {
    includes(UiModule)
}