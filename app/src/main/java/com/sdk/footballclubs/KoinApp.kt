package com.sdk.footballclubs

import android.app.Application
import com.sdk.footballclubs.di.myModule
import org.koin.core.context.startKoin

class KoinApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(myModule)

        }
    }
}