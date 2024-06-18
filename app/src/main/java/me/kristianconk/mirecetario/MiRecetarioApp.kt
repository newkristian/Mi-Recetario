package me.kristianconk.mirecetario

import android.app.Application
import me.kristianconk.mirecetario.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MiRecetarioApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MiRecetarioApp)
            modules(appModule)
        }
    }
}