package com.handcraftedmarket.handcraftedmarket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.handcraftedmarket.handcraftedmarket.R
import com.handcraftedmarket.handcraftedmarket.adapter.ProductAdapter
import com.handcraftedmarket.handcraftedmarket.databinding.FragmentProductBinding
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.viewModel.ProductViewModel

class ProductFragment : BaseFragment(), ProductAdapter.ProductClicked {

    private lateinit var binding: FragmentProductBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: ProductViewModel

    private var productList: ArrayList<Product> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        binding.productRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = ProductAdapter(requireContext(), this)
        binding.productRecycler.adapter = adapter

        viewModel.productList.observe(viewLifecycleOwner) { prodList ->
            if (prodList.isNotEmpty()) {
                productList = prodList
                adapter.updateList(productList)
            }
        }

        viewModel.getProducts()

        return binding.root
    }

    override fun onProductClicked(product: Product) {
        val bundle = bundleOf(Pair("product", product))
        findNavController().navigate(R.id.productDetailFragment, bundle)

    }
}