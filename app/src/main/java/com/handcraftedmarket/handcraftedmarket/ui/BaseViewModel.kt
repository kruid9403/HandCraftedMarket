package com.handcraftedmarket.handcraftedmarket.ui

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.handcraftedmarket.handcraftedmarket.managers.FirebaseManager
import com.handcraftedmarket.handcraftedmarket.model.Customer
import com.google.firebase.auth.FirebaseAuth
import com.handcraftedmarket.handcraftedmarket.model.CartList
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.repos.ProductRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    val firebaseManager = FirebaseManager()
    val auth = FirebaseAuth.getInstance()

    val customer by lazy { MutableLiveData<Customer>() }
    val cartList = mutableStateOf<CartList>(CartList(HashMap()))
    val cartId = mutableStateOf<ArrayList<String>>(arrayListOf())

    val productRepo: ProductRepo by inject()

    init {
        firebaseManager.customerDataLocation()
            .get()
            .addOnSuccessListener {
                if (it.exists()){
                    val user = Customer(auth.uid.toString(), it.data?.get("name").toString(), it.data?.get("imgUrl").toString(),
                        it.data?.get("fcmToken").toString(), it.data?.get("totalOrders").toString().toInt())

                    customer.postValue(user)

                }
            }

        val tempList = ArrayList<String>()
        firebaseManager.cart()
            .get()
            .continueWith {doc ->
                Log.e("BaseVM", doc.result.data.toString())
                if (doc.isSuccessful){
                    doc.result.data?.forEach {
                        tempList.add(it.key.toString())
                    }
                    cartId.value = tempList
                }
            }
    }
}