package com.handcraftedmarket.handcraftedmarket.composeUI.ui.product

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.viewModel.ProductDetailVM

@Composable
fun ProductScreen(navController: NavController?, product: Product) {
    val viewModel: ProductDetailVM = viewModel()

    Log.e("ProdDet", product.toString())
}

@Preview
@Composable
fun ProductScreenPreview(){
    ProductScreen(navController = null, product = Product())
}