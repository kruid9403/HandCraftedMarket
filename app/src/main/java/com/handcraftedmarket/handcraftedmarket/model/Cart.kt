package com.handcraftedmarket.handcraftedmarket.model

import androidx.room.Entity
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Cart(
    var creator: String,
    var products: HashMap<String, Product>
){
    constructor() : this("", hashMapOf())
}