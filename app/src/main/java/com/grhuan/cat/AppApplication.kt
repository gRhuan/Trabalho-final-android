package com.grhuan.cat

import android.app.Application
import com.grhuan.cat.di.daoModule
import com.grhuan.cat.di.networkModule
import com.grhuan.cat.di.repositoryModule
import com.grhuan.cat.di.utilsModule
import com.grhuan.cat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val modules =
            listOf(daoModule, repositoryModule, viewModelModule, networkModule, utilsModule)
        startKoin {
            androidContext(this@AppApplication)
            modules(modules)
        }
    }
}