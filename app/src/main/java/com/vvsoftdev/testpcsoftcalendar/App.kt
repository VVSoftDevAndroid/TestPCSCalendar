package com.vvsoftdev.testpcsoftcalendar

import android.app.Application
import com.vvsoftdev.testpcsoftcalendar.di.instanceModule
import com.vvsoftdev.testpcsoftcalendar.di.repositoryModule
import com.vvsoftdev.testpcsoftcalendar.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(instanceModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
    }
}