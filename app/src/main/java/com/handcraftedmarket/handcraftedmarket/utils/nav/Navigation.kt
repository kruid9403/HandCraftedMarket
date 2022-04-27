package com.handcraftedmarket.handcraftedmarket.utils.nav

import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.auth.LoginScreen
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.cart.CartScreen
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.product.ProductListScreen
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.product.ProductScreen
import com.handcraftedmarket.handcraftedmarket.model.Product

@Composable
fun Navigation(scaffoldState: ScaffoldState, navController: NavHostController) {
    NavHost(navController = navController, startDestination =  Screen.LoginScreen.route){
        composable(Screen.ProductScreen.route){
            ProductScreen(navController = navController)
        }

        composable(Screen.ProductListScreen.route){
            ProductListScreen(navController = navController)
        }

        composable(Screen.LoginScreen.route){
            LoginScreen(navController = navController)
        }

        composable(Screen.CartScreen.route){
            CartScreen(navController = navController)
        }

    }
}