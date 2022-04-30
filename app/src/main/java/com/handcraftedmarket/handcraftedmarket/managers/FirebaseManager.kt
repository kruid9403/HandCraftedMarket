package com.handcraftedmarket.handcraftedmarket.managers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.handcraftedmarket.handcraftedmarket.model.Product
import org.koin.core.component.KoinComponent

class FirebaseManager(): KoinComponent {

    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    fun customerDataLocation() = firestore.collection("customer").document(auth.uid.toString())

    fun productData() = firestore.collection("products")

    fun sellerData() = firestore.collection("sellers")

    fun customerData() = firestore.collection("customer").document(auth.uid.toString())

    fun orderData() = firestore.collection("orders")

    fun cart() = firestore.collection("carts").document(auth.uid.toString())

    fun firebaseToProduct(it: DocumentSnapshot?): Product {
        return it?.toObject(Product::class.java)!!
//        val standard = it?.data!!["productStandard"] as HashMap<*,*>
//        val standardDetails = StandardDetails(attribute = standard["attribute"].toString(), detailsList = standard["detailsList"].toString())
//        return Product(
//            id = it.id!!,
//            name = it.data!!["name"] as String,
//            price = it.data!!["price"] as Double,
//            description = it.data!!["description"] as String,
//            creator = it.data!!["creator"] as String,
//            visible = it.data!!["visible"] as Boolean,
//            saleCount = it.data!!["saleCount"] as Int,
//            backOrder = it.data!!["backOrder"] as Boolean,
//            productStandard =  standardDetails
//
//        )
    }
}