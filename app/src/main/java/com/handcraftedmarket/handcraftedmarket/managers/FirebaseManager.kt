package com.handcraftedmarket.handcraftedmarket.managers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.Koin
import org.koin.core.KoinComponent

class FirebaseManager(): KoinComponent{

    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    fun customerDataLocation() = firestore.collection("customer").document(auth.uid.toString())

    fun productData() = firestore.collection("products")

    fun sellerData() = firestore.collection("sellers")

    fun customerData() = firestore.collection("customer").document(auth.uid.toString())

    fun orderData() = firestore.collection("orders")

    fun cart() = firestore.collection("carts").document(auth.uid.toString())
}