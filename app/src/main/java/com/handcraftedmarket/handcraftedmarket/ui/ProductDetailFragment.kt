package com.handcraftedmarket.handcraftedmarket.ui

import android.os.Bundle
import android.telephony.CarrierConfigManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.firestore.SetOptions
import com.handcraftedmarket.handcraftedmarket.R
import com.handcraftedmarket.handcraftedmarket.adapter.ShopViewCustomAdapter
import com.handcraftedmarket.handcraftedmarket.adapter.ShopViewPhotoAdapter
import com.handcraftedmarket.handcraftedmarket.adapter.ShopViewStandardAdapter
import com.handcraftedmarket.handcraftedmarket.databinding.FragmentProductDetailBinding
import com.handcraftedmarket.handcraftedmarket.managers.FirebaseManager
import com.handcraftedmarket.handcraftedmarket.model.Cart
import com.handcraftedmarket.handcraftedmarket.model.CartList
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.viewModel.ProductDetailVM

class ProductDetailFragment : Fragment(), 
    ShopViewPhotoAdapter.PhotoClicked, 
    ShopViewStandardAdapter.StandardClicked, 
    ShopViewCustomAdapter.OptionsClicked,
    View.OnClickListener
{

    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var photoAdapter: ShopViewPhotoAdapter
    private lateinit var standardAdapter: ShopViewStandardAdapter
    private lateinit var customAdapter: ShopViewCustomAdapter
    private lateinit var product: Product
    private lateinit var viewModel: ProductDetailVM

    private var imgPosition = 0
    private val firebaseManager = FirebaseManager()
    private var cart = CartList(HashMap())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(layoutInflater)

//        if (arguments!= null){
//            product = arguments?.get("product") as Product
//        }
//
//        viewModel = ViewModelProvider(this).get(ProductDetailVM::class.java)
//
//        setViews()
//
//        binding.shopViewImageRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        photoAdapter = ShopViewPhotoAdapter(requireContext(), this, product.imgUrl)
//        binding.shopViewImageRecycler.adapter = photoAdapter
//
//        standardAdapter = ShopViewStandardAdapter(requireContext(), this, product.productStandard)
//        binding.shopViewStandard.layoutManager = LinearLayoutManager(requireContext())
//        binding.shopViewStandard.adapter = standardAdapter
//
//        binding.shopViewCustomize.layoutManager = LinearLayoutManager(requireContext())
////        customAdapter = ShopViewCustomAdapter(requireContext(), this, product.options)
//        binding.shopViewCustomize.adapter = customAdapter
//
//        binding.productDetailAddToCart.setOnClickListener(this)
//
//        viewModel.cartList.observe(viewLifecycleOwner){
//            if (it != null && it.cartList != cart.cartList) {
//                cart = it
//            }
//
//        }

        return binding.root
    }

    override fun onOptionClicked(photo: String, position: Int) {

    }

    override fun onPhotoClicked(photo: String, position: Int) {
    }

    override fun onStandardClicked(photo: String, position: Int) {
    }

    override fun onClick(p0: View?) {
    }

//    fun setViews(){
//        Glide.with(requireContext()).load(product.imgUrl[0]).into(binding.shopViewMainImg)
//        binding.shopViewName.text = product.name
//        binding.shopViewDescription.text = product.description
//        val format = NumberFormat.getCurrencyInstance()
//        format.maximumFractionDigits = 2
//        binding.shopViewPrice.text = format.format(product.price)
//    }
//
//    override fun onOptionClicked(photo: String, position: Int) {
//
//    }
//
//    override fun onPhotoClicked(photo: String, position: Int) {
//    }
//
//    override fun onStandardClicked(photo: String, position: Int) {
//    }
//
//    override fun onClick(v: View?) {
//        when(v){
//            binding.productDetailAddToCart -> {
//                product.purchaseQty += 1
//                if (firebaseManager.auth.currentUser != null) {
//                    cart = viewModel.cartList.value!!
//                    if (cart == CartList()){
//                        cart = CartList(arrayListOf(Cart(product.creator, arrayListOf(product))))
//                        viewModel.cartList.postValue(cart)
//                    }else if (cart.cartList.size == 0){
//                        Log.e("ProductDetailFrag", "click")
//                        cart.cartList.add(Cart(product.creator, arrayListOf(product)))
//                        viewModel.cartList.postValue(cart)
//                        Toast.makeText(requireContext(), "ug", Toast.LENGTH_SHORT).show()
//                    }else{
//                        cart.cartList.forEach {
//                            if (it.creator == product.creator){
//                                var exists = false
//                                it.products.forEach { prod ->
//                                    if (prod.id == product.id){
//                                        Toast.makeText(requireContext(), "Product is already in your cart", Toast.LENGTH_SHORT)
//                                            .show()
//                                        exists = true
//                                    }
//                                }
//
//                                if (!exists) {
//                                    it.products.add(product)
//                                    viewModel.cartList.postValue(cart)
//                                    Toast.makeText(requireContext(), "fuck", Toast.LENGTH_SHORT)
//                                        .show()
//                                }
//                            }
//                        }
//                    }
//
//                    firebaseManager.cart().set(cart).continueWith { result ->
//                        if (result.isSuccessful) {
//                            Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_SHORT)
//                                .show()
//                        } else {
//                            Toast.makeText(
//                                requireContext(),
//                                "An error has occurred, Try Again",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            Log.e("ProductDetail", result.exception.toString())
//                        }
//                    }
//
//                }else{
//                    val bundle = bundleOf(
//                        "product" to product
//                    )
//                    findNavController().navigate(R.id.loginFragment, bundle)
//                }
//            }
//        }
//    }
}