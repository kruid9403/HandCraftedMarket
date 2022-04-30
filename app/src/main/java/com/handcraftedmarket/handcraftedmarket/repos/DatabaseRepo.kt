package com.handcraftedmarket.handcraftedmarket.repos

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.core.component.KoinComponent

abstract class DatabaseRepo<T>: BaseRepo<T>(), KoinComponent {
    protected abstract suspend fun listenToDb(): Flow<RepoResource<T>>

    override suspend fun listenToRepo(): Flow<RepoResource<T>>{
        return listenToDb().distinctUntilChanged()
    }

    override suspend fun storeToCache(t: T) {
        TODO("Not yet implemented")
    }

    override suspend fun nukeProduct() {
        return nukeProduct()
    }

}