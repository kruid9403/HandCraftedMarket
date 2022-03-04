package com.handcraftedmarket.handcraftedmarket.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.handcraftedmarket.handcraftedmarket.R
import com.handcraftedmarket.handcraftedmarket.databinding.FragmentProfileBinding
import com.handcraftedmarket.handcraftedmarket.model.Product

class ProfileFragment : BaseFragment(), View.OnClickListener {

    //TODO: UPLOAD PROFILE DATA
    private lateinit var binding: FragmentProfileBinding

    private lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        product = if (arguments?.get("product") != null){
            arguments?.get("product") as Product
        }else{
            Product()
        }

        binding.profileSkip.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        val bundle = bundleOf(
            "product" to product
        )

        when(v){
            binding.profileSkip -> {
                if (product != Product()){
                    findNavController().navigate(R.id.productDetailFragment, bundle)
                }else{
                    findNavController().navigate(R.id.productFragment2)
                }
            }
        }
    }
}