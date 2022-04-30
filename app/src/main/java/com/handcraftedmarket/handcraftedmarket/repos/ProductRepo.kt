package com.handcraftedmarket.handcraftedmarket.repos

import com.handcraftedmarket.handcraftedmarket.dao.ProductDao
import com.handcraftedmarket.handcraftedmarket.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject

class ProductRepo: DatabaseRepo<Product>() {
    private val productDao: ProductDao by inject()

    public override suspend fun checkCache(): Product? {
        return productDao.getProduct()
    }

    public override suspend fun storeToCache(t: Product) {
        return productDao.insert(t)
    }

    public override suspend fun listenToDb(): Flow<RepoResource<Product>> {
        return productDao.listenToProduct().map { product ->
            RepoResource(data = product)
        }
    }

    public override suspend fun nukeProduct() {
        return productDao.nukeProduct()
    }
}