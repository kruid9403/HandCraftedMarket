package com.handcraftedmarket.handcraftedmarket.ui

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.handcraftedmarket.handcraftedmarket.managers.FirebaseManager
import com.handcraftedmarket.handcraftedmarket.model.Customer
import com.google.firebase.auth.FirebaseAuth
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.repos.ProductRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Arrays

open class BaseViewModel: ViewModel(), KoinComponent {

    val TAG = "BaseVM"

    val firebaseManager = FirebaseManager()
    val auth = FirebaseAuth.getInstance()

    val customer by lazy { MutableLiveData<Customer>() }
    val cartList = mutableStateMapOf<String, Product>()

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

        val tempList = ArrayList<Product>()
        firebaseManager.cart()
            .collection("products")
            .addSnapshotListener { value, error ->
                if(error != null){
                    Log.e("BaseViewModel", error.localizedMessage!!)
                }
                if (value?.documents?.size!! > 0){
                    value.documents.forEach {doc ->
                        val prod = doc.toObject(Product::class.java)
                        cartList[prod?.id!!] = prod
                    }
                }
            }
//            .get()
//            .continueWith {doc ->
//                if (doc.isSuccessful){
//                    doc.result.data?.forEach {
//                        tempList.add(it.key.toString())
//                    }
//                    cartId.value = tempList
//                    getDetails()
//                }
//            }
    }

//    fun getDetails(){
//        cartId.forEach {
//            firebaseManager.productData()
//                .whereEqualTo("id", it)
//                .get()
//                .continueWith{doc ->
//                    val product = doc.result.toObjects(Product::class.java)
//                    product.forEach {prod ->
//                        cartData.add(prod)
//                        Log.e("BaseVM", cartData.size.toString())
//                    }
//                }
//        }
//    }
}