package com.handcraftedmarket.handcraftedmarket.composeUI.ui.product

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.handcraftedmarket.handcraftedmarket.utils.nav.Screen
import com.handcraftedmarket.handcraftedmarket.viewModel.ProductViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoilApi::class)
@Composable
fun ProductListScreen(navController: NavController?) {
    val viewModel: ProductViewModel = viewModel()
    val context = LocalContext.current

    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
        ){
        items(viewModel.productList.sortedBy { it.saleCount }){prod ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable{
                        val bundle = bundleOf(
                            "prod" to prod
                        )
                        navController?.currentBackStackEntry?.arguments?.putAll(bundle)
                        navController?.navigate(Screen.ProductScreen.route)
                    }
            ) {
                Image(
                    painter = rememberImagePainter(data = prod.imgUrl[0]),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .shadow(elevation = 8.dp, shape = CircleShape)
                )

                Text(text = prod.name)

                Text(text = prod.saleCount.toString())
            }
        }
    }
}


@Preview
@Composable
fun ProductListScreenPreview(){
    ProductListScreen(navController = null)
}