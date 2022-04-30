package com.handcraftedmarket.handcraftedmarket.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.io.Serializable

@Keep
@Entity
class Product: Serializable {
    @PrimaryKey var id: String = ""
    var name: String = ""
    var imgUrl: ArrayList<String> = arrayListOf()
    var price: Double = 0.0
    var description: String = ""
    var creator: String = ""
    var visible: Boolean = true
    var saleCount: Int = 0
    var backOrder: Boolean = false
    var productStandard: ArrayList<StandardDetails> = arrayListOf()
    var options: ArrayList<StandardDetails> = arrayListOf()
    var category: String = ""
    var quantity: Int = 0
    var purchaseQty: Int = 0
    var purchaseCheck: Boolean = false

}

@Keep
class ProductOptions:Serializable {
    var attribute: String = ""
    var optionalList: ArrayList<String> = arrayListOf()

}

@Keep
class StandardDetails:Serializable {
    var attribute: String = ""
    var detailsList: ArrayList<String> = arrayListOf()

}

