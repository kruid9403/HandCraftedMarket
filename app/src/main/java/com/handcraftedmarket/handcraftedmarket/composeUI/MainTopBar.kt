package com.handcraftedmarket.handcraftedmarket.composeUI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.ColorOnPrimary
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.ColorPrimary
import com.handcraftedmarket.handcraftedmarket.ui.BaseViewModel
import com.handcraftedmarket.handcraftedmarket.utils.nav.Screen
import kotlinx.coroutines.launch


@Composable
fun MainTopBar(navController: NavController?, scaffoldState: ScaffoldState?) {
    val scope = rememberCoroutineScope()
    val auth = FirebaseAuth.getInstance().currentUser
    val viewModel: BaseViewModel = viewModel()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = ColorPrimary)
    ) {
        val (burger, icon, cart, cartSize) = createRefs()
        FaIcon(
            faIcon = FaIcons.Bars,
            tint = ColorOnPrimary,
            size = 24.dp,
            modifier = Modifier
                .clickable {
                    scope.launch {
                        if (scaffoldState?.drawerState?.isOpen!!){
                            scaffoldState.drawerState.close()
                        }else{
                            scaffoldState.drawerState.open()
                        }
                    }
                }
                .padding(start = 16.dp)
                .constrainAs(burger) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        FaIcon(faIcon = FaIcons.Palette,
            tint = ColorOnPrimary,
            size = 24.dp,
            modifier = Modifier
                .constrainAs(icon){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        if (auth != null) {
            FaIcon(faIcon = FaIcons.ShoppingCart,
                tint = ColorOnPrimary,
                size = 24.dp,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable{
                        navController?.navigate(Screen.CartScreen.route)
                    }
                    .constrainAs(cart) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Row(
                modifier = Modifier
                    .height(15.dp)
                    .width(20.dp)
                    .padding(end = 5.dp)
                    .background(color = Color.Red, shape = CircleShape)
                    .constrainAs(cartSize){
                        end.linkTo(cart.end)
                        bottom.linkTo(cart.top)
                        top.linkTo(cart.top)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
//                Text(
//                    text = viewModel.car.value.size.toString(),
//                    style = TextStyle(
//                        color = Color.White,
//                        fontSize = 10.sp
//                    ),
//                )
            }
        }
    }
}
@Preview
@Composable
fun MainTopBarPreview(){
    MainTopBar(navController = null, scaffoldState = null)
}