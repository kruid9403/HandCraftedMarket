package com.handcraftedmarket.handcraftedmarket.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.SetOptions
import com.handcraftedmarket.handcraftedmarket.dao.ProductDao
import com.handcraftedmarket.handcraftedmarket.model.Cart
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.ui.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductDetailVM(application: Application): BaseViewModel(application), KoinComponent {

    val productDao: ProductDao by inject()
    val prod = mutableStateOf<Product?>(null)

    fun getProduct() {
        viewModelScope.launch {
            prod.value = productDao.getProduct()
        }
    }

    fun addToCart() {
        val data = hashMapOf(
            prod.value?.id!! to prod.value?.creator!!
        )
        firebaseManager.cart()
            .set(data, SetOptions.merge())
            .continueWith {
                Log.e("ProductDetailVM", it.result.toString())
                Log.e("ProductDetailVM", it.exception?.localizedMessage.toString())
            }
    }
}