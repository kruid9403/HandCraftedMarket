package com.handcraftedmarket.handcraftedmarket.model

import androidx.room.Entity
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class CartList(
    var cartList: ArrayList<Cart>
){
    constructor(): this(ArrayList())
}