package com.handcraftedmarket.handcraftedmarket.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.repos.ProductRepo
import com.handcraftedmarket.handcraftedmarket.ui.BaseViewModel
import com.handcraftedmarket.handcraftedmarket.utils.nav.Screen
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductViewModel(application: Application): BaseViewModel(application), KoinComponent {

    val productList = mutableStateListOf<Product>()
    val productRepo: ProductRepo by inject()

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

    fun saveProduct(product: Product, navController: NavController?){
        viewModelScope.launch {
            productRepo.storeToCache(product)
            navController?.navigate(Screen.ProductScreen.route)
        }
    }
}