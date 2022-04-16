package com.handcraftedmarket.handcraftedmarket.composeUI

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.drawer.NavDrawer
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.HandCraftedMarketTheme
import com.handcraftedmarket.handcraftedmarket.utils.nav.Navigation

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppComponent()
        }
    }
}

@Composable
fun AppComponent() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    HandCraftedMarketTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            content = {
                Navigation(
                    scaffoldState = scaffoldState,
                    navController = navController)
                      },
            drawerContent = {
                NavDrawer(scaffoldState = scaffoldState, navController = navController)
            },
            drawerGesturesEnabled = true,
            topBar = { MainTopBar(navController = navController, scaffoldState = scaffoldState) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HandCraftedMarketTheme {

    }
}