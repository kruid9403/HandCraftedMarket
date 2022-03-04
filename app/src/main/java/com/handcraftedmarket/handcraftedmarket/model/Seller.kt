package com.handcraftedmarket.handcraftedmarket.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.handcraftedmarket.handcraftedmarket.model.shipEngine.ShipFrom
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Seller(
    @PrimaryKey var id: String,
    var name: String = "",
    var shopName: String = "",
    var imgUrl: String = "",
    var fcmToken: String = "",
    val rating: Double = 0.0,
    val stripeAct: String = "",
    var description: String = "",
    var shipping: ShipFrom?,
    val email: String
){
    constructor(): this("","","","","",0.0,"","",ShipFrom(),"")
}

