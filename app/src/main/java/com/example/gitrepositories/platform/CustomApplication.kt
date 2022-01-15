package com.example.gitrepositories.platform

import android.app.Application
import com.example.gitrepositories.di.networkModule
import com.example.gitrepositories.di.repositoryModule
import com.example.gitrepositories.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger()
            androidContext(this@CustomApplication)
            modules(listOf(
                networkModule,
                viewModelModule,
                repositoryModule
            ))
        }
    }
}