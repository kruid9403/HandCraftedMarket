package com.handcraftedmarket.handcraftedmarket.ui

import com.handcraftedmarket.handcraftedmarket.adapter.ProductAdapter

class ProductFragment : BaseFragment(){

//    private lateinit var binding: FragmentProductBinding
    private lateinit var adapter: ProductAdapter
//    private lateinit var viewModel: ProductViewModel

//    private var productList: ArrayList<Product> = ArrayList()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
////        binding = FragmentProductBinding.inflate(layoutInflater)
////        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
////
////        binding.productRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
////        adapter = ProductAdapter(requireContext(), this)
////        binding.productRecycler.adapter = adapter
////
////        viewModel.productList.observe(viewLifecycleOwner) { prodList ->
////            if (prodList.isNotEmpty()) {
////                productList = prodList
////                adapter.updateList(productList)
////            }
////        }
//
//        viewModel.getProducts()
//
//        return binding.root
//    }
//
//    override fun onProductClicked(product: Product) {
//        val bundle = bundleOf(Pair("product", product))
//        findNavController().navigate(R.id.productDetailFragment, bundle)
//
//    }
}