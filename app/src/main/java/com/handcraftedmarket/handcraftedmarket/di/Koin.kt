package com.handcraftedmarket.handcraftedmarket.di

import androidx.room.Room
import com.handcraftedmarket.handcraftedmarket.db.Converters
import com.handcraftedmarket.handcraftedmarket.db.Database
import com.handcraftedmarket.handcraftedmarket.repos.ProductRepo
import com.handcraftedmarket.handcraftedmarket.ui.BaseViewModel
import com.handcraftedmarket.handcraftedmarket.utils.coroutines.CoroutineConfig
import com.handcraftedmarket.handcraftedmarket.utils.coroutines.CoroutineConfigImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val PROVIDER_DB = "ProviderDb"

val appModule = module {
    single {
        Room.databaseBuilder(androidContext(),
            Database::class.java,
            PROVIDER_DB
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single{ get<Database>().productDao() }
    single<CoroutineConfig> { CoroutineConfigImpl() }
    factory { ProductRepo() }
}