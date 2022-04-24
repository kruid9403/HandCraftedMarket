package com.handcraftedmarket.handcraftedmarket.composeUI.ui.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.Niconne
import com.handcraftedmarket.handcraftedmarket.viewModel.ProductListVM

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoilApi::class)
@Composable
fun ProductListScreen(navController: NavController?) {
    val viewModel: ProductListVM = viewModel()
    val context = LocalContext.current
    val navigating = remember { mutableStateOf(false)}

    if(!navigating.value) {
        viewModel.nukeProduct()
    }

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        items(viewModel.productList) { prod ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        viewModel.saveProduct(product = prod, navController = navController, navigating = navigating)
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = rememberImagePainter(data = prod.imgUrl[0]),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .shadow(elevation = 12.dp, shape = CircleShape)
                )

                Text(
                    text = prod.name,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = Niconne,
                        textAlign = TextAlign.Center
                    ),
                    maxLines = 3
                )
            }
        }
    }
}



@Preview
@Composable
fun ProductListScreenPreview(){
    ProductListScreen(navController = null)
}