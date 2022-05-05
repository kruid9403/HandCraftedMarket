package com.handcraftedmarket.handcraftedmarket.composeUI.ui.cart

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.ColorPrimary
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.Niconne
import com.handcraftedmarket.handcraftedmarket.model.Product
import com.handcraftedmarket.handcraftedmarket.utils.toDollarString

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CartScreen(navController: NavController?) {

    val TAG = "CartScreen"

    val viewModel: CartVM = viewModel()
    val purchaseProd = remember { mutableStateOf<Product?>(null) }
    val shippingCheck = remember { mutableStateOf(false) }
    val prodSelected = mutableStateMapOf<String, Boolean>()
    val dialogVis = remember { mutableStateOf(false)}

    if (shippingCheck.value && purchaseProd.value != null){
//        viewModel.getShipping(purchaseProd.value)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (text, spacer, items, buyBtn, dialog) = createRefs()

        Text(
            text = "My Cart",
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = Niconne
            ),
            modifier = Modifier
                .constrainAs(text){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier
                .constrainAs(spacer){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(text.bottom)
                }
                .fillMaxWidth()
                .height(.2.dp)
                .background(color = Color.White)
        )

        LazyColumn(
            modifier = Modifier
                .constrainAs(items){
                    top.linkTo(spacer.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(buyBtn.top)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth()
        ) {
            items(
                viewModel.cartList.values.toList().ifEmpty {
                    arrayListOf<Product>()
                }
            ) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        val (img, text, checkBox) = createRefs()
                        Image(
                            painter = rememberImagePainter(item.imgUrl[0]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .constrainAs(img) {
                                    start.linkTo(parent.start)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                                .height(75.dp)
                                .width(75.dp)
                                .shadow(elevation = 8.dp, shape = CircleShape)
                        )

                        Column(
                            modifier = Modifier
                                .constrainAs(text) {
                                    top.linkTo(parent.top)
                                    end.linkTo(checkBox.start)
                                    start.linkTo(img.end)
                                    bottom.linkTo(parent.bottom)
                                    width = Dimension.fillToConstraints
                                }
                                .padding(start = 8.dp, end = 8.dp)
                        ) {
                            Text(
                                text = item.name,
                                style = TextStyle(
                                    color = Color.White
                                )
                            )

                            Text(
                                text = item.price.toDollarString(),
                                style = TextStyle(
                                    color = Color.White
                                )
                            )
                        }


                        Checkbox(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .constrainAs(checkBox){
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                },
                            checked = prodSelected[item.id] ?: false,
                            onCheckedChange = {checked ->
                               prodSelected[item.id] = checked

                            }
                        )


                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(.2.dp)
                            .background(color = Color.White)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .constrainAs(buyBtn){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(50.dp)
                .background(color = ColorPrimary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = "Order Now",
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = Niconne
                )
            )
        }
    }
}

@Preview
@Composable
fun CartScreenPreview(){
    CartScreen(navController = null)
}