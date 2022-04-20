package com.handcraftedmarket.handcraftedmarket.composeUI.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.handcraftedmarket.handcraftedmarket.model.StandardDetails

@Composable
fun OptionsRow(option: StandardDetails) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = option.attribute,
            modifier = Modifier
                .padding(end = 16.dp)
        )

       Column {
           option.detailsList.forEach { detail ->
               if (detail != "") {
                   Text(
                       text = detail,
                       style = TextStyle(
                           fontFamily = FontFamily.Default
                       )
                   )
               }
           }
       }
    }
}