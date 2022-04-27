package com.handcraftedmarket.handcraftedmarket.composeUI.ui.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.handcraftedmarket.handcraftedmarket.utils.nav.Screen

@Composable
fun LoginScreen(navController: NavController?) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    val email = remember { mutableStateOf("")}
    val password = remember { mutableStateOf("")}
    val authChecked = remember { mutableStateOf(false)}

    if (auth.currentUser != null && !authChecked.value){
        navController?.navigate(Screen.ProductListScreen.route)
        authChecked.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email.value,
            onValueChange = {email.value = it},
            label = { Text(text = "Email")}
        )

        TextField(
            value = password.value,
            onValueChange = {password.value = it},
            label = { Text(text = "Password")}
        )

        Text(text = "Login",
            style = TextStyle(
                color = Color.White
            ),
            modifier = Modifier
                .clickable{
                    auth.signInWithEmailAndPassword(email.value, password.value)
                        .continueWith {
                            if (it.isSuccessful){
                                navController?.navigate(Screen.ProductListScreen.route)
                            }else{

                                Toast.makeText(context, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                            }
                        }
                }
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen(navController = null)
}