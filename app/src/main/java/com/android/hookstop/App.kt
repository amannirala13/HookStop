package com.android.hookstop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class App : ComponentActivity() {
    lateinit var navController: NavHostController 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HookStopTheme {
                navController = rememberNavController()

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