package com.jmoreno.list.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class JosiahPhunwareApplication : Application()
    //, SingletonImageLoader.Factory
    {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@JosiahPhunwareApplication)
            modules(AppModule)
        }
    }
}