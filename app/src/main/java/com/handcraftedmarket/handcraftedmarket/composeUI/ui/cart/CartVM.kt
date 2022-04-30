package com.handcraftedmarket.handcraftedmarket.composeUI.ui.cart

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import com.handcraftedmarket.handcraftedmarket.managers.FirebaseManager
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.ui.BaseViewModel

class CartVM(application: Application): BaseViewModel(application) {

    val cart = SnapshotStateList<Product>()
    val cartIdCart = mutableStateListOf<String>()
   init {
       firebaseManager.cart()
           .get()
           .continueWith {doc ->
               if (doc.isSuccessful){
                   doc.result.data?.forEach {
                       cartIdCart.add(it.key.toString())
                   }
                   cartDetails()
               }
           }
   }

    fun cartDetails(){
        cartIdCart.forEach {
            firebaseManager.productData()
                .whereEqualTo("id", it)
                .get()
                .continueWith{doc ->
                    val product = doc.result.toObjects(Product::class.java)
                    product.forEach {prod ->
                        cart.add(prod)
                        Log.e("CartScreenVM", cart.size.toString())
                    }
                }
        }
    }


}