package com.handcraftedmarket.handcraftedmarket.dao

import androidx.room.Dao
import androidx.room.Query
import com.handcraftedmarket.handcraftedmarket.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao: AbstractDao<Product> {
    @Query("SELECT * FROM 'product' LIMIT 1")
    fun getProduct(): Product?

    @Query("SELECT * FROM 'product' LIMIT 1")
    fun listenToProduct(): Flow<Product?>

    @Query("DELETE FROM 'product'")
    fun nukeProduct()
}