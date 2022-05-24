package com.android.hookstop.nav

sealed class Screen(val route: String){
    object SplashScreen: Screen("SplashScreen")
    object LoginScreen: Screen("LoginScreen")
    object MainActivity: Screen("MainActivity")
}