package com.handcraftedmarket.handcraftedmarket.utils.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.product.ProductListScreen
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.product.ProductScreen
import com.handcraftedmarket.handcraftedmarket.model.Product

@Composable
fun Navigation(scaffoldState: ScaffoldState, navController: NavHostController) {
    NavHost(navController = navController, startDestination =  Screen.ProductListScreen.route){
        composable(Screen.ProductScreen.route){
            if (navController.previousBackStackEntry?.arguments?.getSerializable("prod") != null) {
                val product =
                    navController.previousBackStackEntry?.arguments?.getSerializable("prod") as Product
                ProductScreen(navController = navController, product = product)
            }
        }

        composable(Screen.ProductListScreen.route){
            ProductListScreen(navController = navController)
        }

    }
}