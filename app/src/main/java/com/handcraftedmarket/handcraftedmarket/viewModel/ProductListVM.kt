package com.handcraftedmarket.handcraftedmarket.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.produceState
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.ui.BaseViewModel
import com.handcraftedmarket.handcraftedmarket.utils.nav.Screen
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class ProductListVM(application: Application): BaseViewModel(application = application), KoinComponent {

    val productList = mutableStateListOf<Product>()

    init {
        firebaseManager.productData().whereEqualTo("visible", true)
            .get().addOnSuccessListener { docs ->
                docs.documents.map {
                    firebaseManager.firebaseToProduct(it)
                    val product = it.toObject(Product::class.java)
                    product?.imgUrl?.removeAll(arrayListOf(""))
                    productList.add(product!!)
                }
            }
            .addOnFailureListener { Log.e("ProductVM", it.localizedMessage) }

    }


    fun saveProduct(
        product: Product,
        navController: NavController?,
        navigating: MutableState<Boolean>
    ){
        viewModelScope.launch {
            navigating.value = true
            productRepo.nukeProduct()
            productRepo.storeToCache(product)
            navController?.navigate(Screen.ProductScreen.route)
        }
    }

    fun nukeProduct(){
        viewModelScope.launch {
            productRepo.nukeProduct()
        }
    }
}