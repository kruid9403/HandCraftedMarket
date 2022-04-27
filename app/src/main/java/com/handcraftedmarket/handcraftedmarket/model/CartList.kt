package com.handcraftedmarket.handcraftedmarket.model

import androidx.annotation.Keep
import androidx.room.Entity
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Keep
@Entity
data class CartList (
    var cartList: HashMap<String, HashMap<String, Product>>
): Serializable