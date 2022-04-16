package com.handcraftedmarket.handcraftedmarket.composeUI.ui.product

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.viewModel.ProductDetailVM

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductScreen(navController: NavController?, product: Product) {
    val viewModel: ProductDetailVM = viewModel()

    Log.e("ProdScreen", "product.imgUrl.toString()")
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyRow(
            content = {
                items(product.imgUrl){url ->
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Log.e("ProdScreen", url)
                        Image(
                            painter = rememberImagePainter(data = url),
                            contentDescription = "Product Image",
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .shadow(elevation = 8.dp, shape = CircleShape)
                        )
                    }
                }
            })

    }

    Log.e("ProdDet", product.toString())
}

@Preview
@Composable
fun ProductScreenPreview(){
    ProductScreen(navController = null, product = Product())
}