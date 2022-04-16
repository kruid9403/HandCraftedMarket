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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.Niconne
import com.handcraftedmarket.handcraftedmarket.utils.nav.Screen
import com.handcraftedmarket.handcraftedmarket.viewModel.ProductViewModel
import io.branch.referral.util.Product

@OptIn(ExperimentalFoundationApi::class, ExperimentalCoilApi::class)
@Composable
fun ProductListScreen(navController: NavController?, product: Product?) {
    val viewModel: ProductViewModel = viewModel()
    val context = LocalContext.current

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
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
                        .height(150.dp)
                        .width(150.dp)
                        .shadow(elevation = 8.dp, shape = CircleShape)
                )

                Text(text = prod.name,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = Niconne
                    )
                )

                Text(text = prod.saleCount.toString(),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = Niconne
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun ProductListScreenPreview(){
    ProductListScreen(navController = null, product = null)
}