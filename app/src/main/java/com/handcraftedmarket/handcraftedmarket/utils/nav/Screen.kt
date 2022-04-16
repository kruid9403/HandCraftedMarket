package com.handcraftedmarket.handcraftedmarket.utils.nav

sealed class Screen(val route: String) {
    object SearchScreen: Screen("search_screen")
    object SettingsScreen: Screen("settings_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object ShopScreen: Screen("shop_screen")
    object ProductScreen: Screen("product_screen")
    object ProductListScreen: Screen("product_list_screen")

}