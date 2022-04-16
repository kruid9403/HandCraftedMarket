package com.handcraftedmarket.handcraftedmarket.composeUI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.ColorOnPrimary
import com.handcraftedmarket.handcraftedmarket.composeUI.ui.theme.ColorPrimary
import kotlinx.coroutines.launch


@Composable
fun MainTopBar(navController: NavController?, scaffoldState: ScaffoldState?) {
    val scope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = ColorPrimary)
    ) {
        val (burger, icon) = createRefs()
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
    }
}
@Preview
@Composable
fun MainTopBarPreview(){
    MainTopBar(navController = null, scaffoldState = null)
}