package com.handcraftedmarket.handcraftedmarket.composeUI.ui.product

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.ColorOnPrimary
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.Niconne
import com.handcraftedmarket.handcraftedmarket.viewModel.ProductDetailVM

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductScreen(navController: NavController?) {

    val viewModel: ProductDetailVM = viewModel()
    viewModel.getProduct()

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val primeImage = remember { mutableStateOf(viewModel.product.value?.imgUrl?.get(0)) }
    val standardVis = remember { mutableStateOf(false) }
    val standardOpenState = remember { mutableStateOf(false) }


    val config = LocalConfiguration.current
    val sizeAn by animateSizeAsState(
        targetValue =
        if(!standardOpenState.value){
            Size(config.screenWidthDp.toFloat(), 0f)
        }else{
            Size(config.screenWidthDp.toFloat(), 50f)
        },
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )

    BackHandler {
        navController?.popBackStack()
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(top = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painter = rememberImagePainter(data = primeImage.value),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(250.dp)
                .width(250.dp)
                .shadow(elevation = 8.dp, shape = CircleShape)
        )

        LazyRow(
            content = {
                items(
                    if(viewModel.product.value?.imgUrl != null) {
                        viewModel.product.value?.imgUrl!!
                    }else{
                        arrayListOf()
                    }
                ){url ->
                    if (url != "") {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Log.e("ProdScreen", url)
                            Image(
                                painter = rememberImagePainter(data = url),
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(50.dp)
                                    .shadow(elevation = 8.dp, shape = CircleShape)
                                    .clickable {
                                        primeImage.value = url
                                    }
                            )
                        }
                    }
                }
            }
        )

        Text(text = viewModel.product.value?.name!!,
            style = TextStyle(
                fontFamily = Niconne,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
            ),
            maxLines = 3,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        )

        Text(text = viewModel.product.value?.description!!,
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )

        Row(
            modifier = Modifier
                .clickable {
                    standardVis.value = !standardVis.value
                }
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Standard Options",
                style = TextStyle(
                    fontFamily = Niconne,
                    fontSize = 24.sp
                ),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            )

            FaIcon(faIcon = FaIcons.CaretDown,
                tint = ColorOnPrimary,
                modifier = Modifier
                    .clickable{

                    }
            )
        }

        viewModel.product.value?.productStandard?.forEach { option ->
            OptionsRow(option = option)
        }


//        LazyColumn(
//            content = {
//                items(viewModel.product.value?.productStandard!!){ standard ->
//                    Row(
//                        modifier = Modifier
//                    ){
//                        Text(text = standard.attribute,
//                            modifier = Modifier
//                                .padding(end = 16.dp)
//                        )
//
//                        LazyColumn(
//                            content = {
//                                items(standard.detailsList!!){ standardItem ->
//                                    Text(text = standardItem)
//                                }
//                            },
//                            modifier = Modifier
//                                .height(25.dp)
//                        )
//                    }
//                }
//            }
//        )
    }
}

@Preview
@Composable
fun ProductScreenPreview(){
    ProductScreen(navController = null)
}