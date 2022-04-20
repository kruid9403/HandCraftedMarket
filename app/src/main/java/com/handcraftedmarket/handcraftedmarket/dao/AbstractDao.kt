package com.handcraftedmarket.handcraftedmarket.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface AbstractDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<T>)

    @Delete
    fun delete(vararg obj: T)

    @Delete
    fun delete(list: List<T>)
}