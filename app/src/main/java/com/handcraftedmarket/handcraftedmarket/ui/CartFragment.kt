package com.handcraftedmarket.handcraftedmarket.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.handcraftedmarket.handcraftedmarket.R
import com.handcraftedmarket.handcraftedmarket.adapter.CartAdapter
import com.handcraftedmarket.handcraftedmarket.databinding.FragmentCartBinding
import com.handcraftedmarket.handcraftedmarket.model.CartList
import com.handcraftedmarket.handcraftedmarket.viewModel.CartVM

class CartFragment : BaseFragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartVM
    private lateinit var adapter: CartAdapter

    private var cartList = CartList(HashMap())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(CartVM::class.java)



//        viewModel.cartList.observe(viewLifecycleOwner){
//            if (!setRecycler){
//                binding.cartRecycler.layoutManager = LinearLayoutManager(requireContext())
//                adapter = CartAdapter(requireContext(), it)
//                binding.cartRecycler.adapter = adapter
//                setRecycler = true
//            }else {
//                adapter.updateData(it)
//            }
//        }






        return binding.root
    }
}