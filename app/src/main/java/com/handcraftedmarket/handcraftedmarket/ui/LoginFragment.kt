package com.handcraftedcreator.handcraftedcreator.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.handcraftedmarket.handcraftedmarket.R
import com.handcraftedmarket.handcraftedmarket.databinding.FragmentLoginBinding
import com.handcraftedmarket.handcraftedmarket.managers.FirebaseManager
import com.handcraftedmarket.handcraftedmarket.model.Product

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentLoginBinding

    private val firebaseManager = FirebaseManager()
    private lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.loginBtn.setOnClickListener(this)
        binding.loginForgot.setOnClickListener(this)
        binding.loginRegister.setOnClickListener(this)

        if(arguments!= null){
            product = arguments?.get("product") as Product
        }else{
            product = Product()
        }

        return binding.root
    }

    override fun onClick(v: View?) {
        var bundle = bundleOf()
        if (product != Product()){
            bundle = bundleOf(
                "product" to product
            )
        }
        when(v){
            binding.loginBtn -> {
                if (binding.loginEmail.text.toString().trim() != "" && binding.loginPassword.toString().trim() != "") {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        binding.loginEmail.text.toString().trim(),
                        binding.loginPassword.text.toString().trim()
                    )
                        .addOnSuccessListener {
                            firebaseManager.customerData().get()
                                .addOnSuccessListener { doc ->
                                if (doc.exists()) {
                                    if (product == Product()) {
                                        findNavController().navigate(R.id.productFragment2)
                                    }else{
                                        findNavController().navigate(R.id.productDetailFragment, bundle)
                                    }
                                } else {

                                    findNavController().navigate(R.id.profileFragment, bundle)
                                }
                            }
                                .addOnFailureListener { findNavController().navigate(R.id.profileFragment) }

                        }
                        .addOnFailureListener {
                            findNavController().navigate(R.id.profileFragment, bundle)
                        }
                }
            }

            binding.loginRegister -> {
                findNavController().navigate(R.id.registerFragment, bundle)
            }

            binding.loginForgot -> {
                if (binding.loginEmail.text.toString().trim() != "") {
                    FirebaseAuth.getInstance()
                        .sendPasswordResetEmail(binding.loginEmail.text.toString().trim())
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Email Sent", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }
}