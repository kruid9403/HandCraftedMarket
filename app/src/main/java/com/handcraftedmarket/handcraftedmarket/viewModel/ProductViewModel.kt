package com.handcraftedmarket.handcraftedmarket.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.ui.BaseViewModel

class ProductViewModel(application: Application): BaseViewModel(application) {

    val productList by lazy { MutableLiveData<ArrayList<Product>>() }

    fun getProducts(){
        var list = ArrayList<Product>()
        firebaseManager.productData().whereEqualTo("visible", true)
            .get().addOnSuccessListener { docs ->
                docs.documents.map {
                    val product = it.toObject(Product::class.java)
                    product?.imgUrl?.removeAll(arrayListOf(""))
                    list.add(product!!)
                    Log.e("ProductVM", product.toString())
                }
                productList.postValue(list)
        }
            .addOnFailureListener { Log.e("ProductVM", it.localizedMessage) }
    }
}