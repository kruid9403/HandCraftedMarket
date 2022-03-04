package com.handcraftedmarket.handcraftedmarket.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.flow.Flow

@Entity
@JsonClass(generateAdapter = true)
data class Customer(
    @PrimaryKey var id: String,
    var name: String = "",
    var imgUrl: String = "",
    var fcmToken: String = "",
    val totalOrders: Int = 0
)

