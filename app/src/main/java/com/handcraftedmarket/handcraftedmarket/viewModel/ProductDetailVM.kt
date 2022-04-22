package com.handcraftedmarket.handcraftedmarket.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.handcraftedmarket.handcraftedmarket.dao.ProductDao
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.repos.ProductRepo
import com.handcraftedmarket.handcraftedmarket.ui.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductDetailVM(application: Application): BaseViewModel(application), KoinComponent {

    val productDao: ProductDao by inject()

    fun getProduct() {
        viewModelScope.launch {
            product.value = productDao.getProduct()
        }

    }

    fun nukeProduct(){
        viewModelScope.launch {
            productDao.nukeProduct()
        }
    }
}