package com.handcraftedmarket.handcraftedmarket.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Order(
    @PrimaryKey var id: String,
    var customer: Customer = Customer("","","","",0),
    var products: List<Product> = ArrayList()
)

