package com.android.hookstop.screens.auth

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.android.hookstop.components.AppBar
import com.android.hookstop.components.Buttons
import com.android.hookstop.nav.Screen

@Composable
fun LoginScreen(navController: NavController?){
    val context =  LocalContext.current as? Activity
    AppBar(title = "Login", secondaryTitle = "We need to identify you before you can enjoy the breeze" ){
        Buttons.ExitButton {
              context?.run{ finish() }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview(){
    LoginScreen(null)
}