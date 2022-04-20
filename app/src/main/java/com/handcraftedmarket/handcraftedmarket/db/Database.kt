package com.handcraftedmarket.handcraftedmarket.db

import androidx.room.*
import androidx.room.Database
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.handcraftedmarket.handcraftedmarket.dao.ProductDao
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.model.ProductOptions
import com.handcraftedmarket.handcraftedmarket.model.StandardDetails
import java.lang.reflect.Type

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class Database: RoomDatabase(){
    abstract fun productDao(): ProductDao
}

class Converters{

    @TypeConverter
    fun listToJson(value: ArrayList<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): ArrayList<String> = Gson().fromJson(value, ArrayList<String>()::class.java)

    @TypeConverter
    fun standardDetailsToString(value: ArrayList<StandardDetails>): String = Gson().toJson(value)

    @TypeConverter
    fun stringToStandardDetails(value: String): ArrayList<StandardDetails> {
        val type = object: TypeToken<ArrayList<StandardDetails>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun productOptionsToString(value: ArrayList<ProductOptions>): String = Gson().toJson(value)

    @TypeConverter
    fun stringToProductOptions(value: String): ArrayList<ProductOptions> {
        val type = object: TypeToken<ArrayList<StandardDetails>>() {}.type
        return Gson().fromJson(value, type)
    }
}