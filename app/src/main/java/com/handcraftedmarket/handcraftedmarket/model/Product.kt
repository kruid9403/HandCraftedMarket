package com.handcraftedmarket.handcraftedmarket.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Entity
@JsonClass(generateAdapter = true)
data class Product(
    @PrimaryKey var id: String,
    var name: String,
    var imgUrl: ArrayList<String>,
    var price: Double,
    var description: String,
    var creator: String,
    var visible: Boolean,
    var saleCount: Int,
    var backOrder: Boolean,
    var productStandard: ArrayList<StandardDetails>,
    var options: ArrayList<ProductOptions>,
    var category: String,
    var quantity: Int,
    var purchaseQty: Int
):Serializable{
    constructor() : this("","",ArrayList(),0.0,"","",true,0,true,
        ArrayList(), ArrayList(), "none", 0, 0
    )
}


@Entity
@JsonClass(generateAdapter = true)
data class ProductOptions(
    var attribute: String,
    var optionalList: ArrayList<String>
): Serializable {
    constructor(): this("", ArrayList())
}

@Entity
@JsonClass(generateAdapter = true)
data class StandardDetails(
    var attribute: String,
    var detailsList: ArrayList<String>
): Serializable{
    constructor():this("", ArrayList())
}

