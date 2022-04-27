package com.handcraftedmarket.handcraftedmarket.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.handcraftedmarket.handcraftedmarket.R
import com.handcraftedmarket.handcraftedmarket.managers.FirebaseManager
import com.handcraftedmarket.handcraftedmarket.model.Cart
import com.handcraftedmarket.handcraftedmarket.model.CartList
import com.handcraftedmarket.handcraftedmarket.model.Seller

class CartAdapter (val context: Context, val cartList: CartList):
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    val firebaseManager = FirebaseManager()
    var list = cartList.cartList

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(cart: Cart, position: Int){
            var seller = Seller()
            val name = itemView.findViewById<TextView>(R.id.item_cart_shop_name)
            firebaseManager.sellerData().document(cart.creator)
                .get()
                .continueWith {
                    if (it.isSuccessful){
                        seller = it.result.toObject(Seller::class.java)!!
                        name.text = seller.shopName
                        Log.e("CartAdapter", seller.toString())
                    }else{
                        Log.e("CartAdapter", it.exception.toString())

                    }
                }

            val recycler = itemView.findViewById<RecyclerView>(R.id.item_cart_product_recycler)
            recycler.layoutManager = LinearLayoutManager(context)
//            val adapter = CartProductAdapter(context, cart.products)
//            recycler.adapter = adapter

        }
    }

    fun updateData(newList: CartList){
        list = newList.cartList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_main_cart_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val cart = list[position]
//        holder.bind(cart, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}