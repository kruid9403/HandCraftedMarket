package com.handcraftedmarket

import android.app.Application
import com.handcraftedmarket.handcraftedmarket.di.appModule
import io.branch.referral.Branch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Branch.enableTestMode()
        Branch.getAutoInstance(this)

        val modules: List<Module> = listOf(appModule)

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(modules = modules)
        }

    }
}