package com.android.hookstop.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.hookstop.screens.auth.LoginScreen
import com.android.hookstop.screens.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(route = Screen.SplashScreen.route){ SplashScreen(navController) }
        composable(route = Screen.LoginScreen.route){ LoginScreen(navController) }
    }
}