package com.handcraftedcreator.handcraftedcreator.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.handcraftedmarket.handcraftedmarket.R
import com.handcraftedmarket.handcraftedmarket.databinding.FragmentRegisterBinding
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.ui.BaseFragment

class RegisterFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        binding.registerBtn.setOnClickListener(this)
        binding.registerLogin.setOnClickListener(this)

        if (arguments?.get("product") != null){
            product = arguments?.get("product") as Product
        }else{
            product = Product()
        }

        return binding.root
    }

    override fun onClick(v: View?) {
        val bundle = bundleOf(
            "product" to product
        )
        when(v){
            binding.registerBtn -> {
                auth.createUserWithEmailAndPassword(
                    binding.registerEmail.text.toString().trim(),
                    binding.registerPassword.text.toString().trim()
                )
                    .addOnSuccessListener {
                        findNavController().navigate(R.id.loginFragment, bundle)
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
            }
            binding.registerLogin -> {
                findNavController().navigate(R.id.loginFragment, bundle)
            }
        }
    }
}