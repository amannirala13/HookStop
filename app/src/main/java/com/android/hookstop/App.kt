package com.android.hookstop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.hookstop.ui.theme.HookStopTheme
import com.android.hookstop.nav.NavGraph
import com.android.hookstop.nav.Screen
import com.android.hookstop.ui.theme.Red
import com.android.hookstop.ui.theme.Yellow
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class App : ComponentActivity() {
    lateinit var systemUiController : SystemUiController
    lateinit var navController: NavHostController 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HookStopTheme {
                navController = rememberNavController()
                systemUiController = rememberSystemUiController()

                val accent = Yellow
                val backgroundColor = MaterialTheme.colorScheme.background
                val useDarkIcons = !isSystemInDarkTheme()

                navController.addOnDestinationChangedListener { _, destination, _ ->
                   if(destination.route != Screen.SplashScreen.route) {
                       systemUiController.setSystemBarsColor(
                           color = backgroundColor,
                           darkIcons = useDarkIcons,
                           isNavigationBarContrastEnforced = true,
                       )
                       systemUiController.isNavigationBarVisible = true // Navigation bar
                   }
                    else {
                       systemUiController.setSystemBarsColor(
                           color = accent,
                           darkIcons = false,
                           isNavigationBarContrastEnforced = false,
                       )
                       systemUiController.isNavigationBarVisible = false // Navigation bar
                   }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   NavGraph(navController = navController)
                }
            }
        }
    }
    
    @Preview
    @Composable
    fun AppPrev(){
        NavGraph(navController = navController)
    }
}