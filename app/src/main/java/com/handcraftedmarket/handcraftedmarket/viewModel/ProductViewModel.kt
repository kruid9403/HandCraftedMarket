package com.handcraftedmarket.handcraftedmarket.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.ui.BaseViewModel

class ProductViewModel(application: Application): BaseViewModel(application) {

    val productList = mutableStateListOf<Product>()
    init {
        firebaseManager.productData().whereEqualTo("visible", true)
            .get().addOnSuccessListener { docs ->
                docs.documents.map {
                    val product = it.toObject(Product::class.java)
                    product?.imgUrl?.removeAll(arrayListOf(""))
                    productList.add(product!!)
                }
            }
            .addOnFailureListener { Log.e("ProductVM", it.localizedMessage) }
    }

    fun getProducts(){


    }
}