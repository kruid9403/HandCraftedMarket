package com.handcraftedmarket.handcraftedmarket.composeUI.ui.drawer

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun NavDrawer(scaffoldState: ScaffoldState?, navController: NavHostController?) {
}

@Preview
@Composable
fun NavDrawerPreview(){
    NavDrawer(scaffoldState = null, navController = null)
}