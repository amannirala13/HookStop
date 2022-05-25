package com.android.hookstop.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.hookstop.nav.Screen
import com.android.hookstop.values.Duration
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.hookstop.R
import com.android.hookstop.components.animation.AnimatedLogo
import com.android.hookstop.ui.theme.*
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController?) {
    var startAnimation by remember { mutableStateOf(false) }
    var startCircleAnimation by remember { mutableStateOf(false)}

    val circleAnim = animateFloatAsState(targetValue = if (startCircleAnimation) 2f else 0f,
        animationSpec = tween(durationMillis = Duration.SplashScreenCircleAnimationDuration.value.toInt())
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(Duration.SplashScreenCartAnimationDuration.value)
        startCircleAnimation = true
        delay(Duration.SplashScreenDuration.value)
        navController?.let{
            it.navigate(Screen.LoginScreen.route){
                this.popUpTo(Screen.SplashScreen.route){ this.inclusive = true }
            }
        }
    }

    Splash(startAnimation, circleAnim.value)
}

@Composable
fun Splash(startAnimation: Boolean,
           circleAnimValue: Float){

    val backgroundColor = MaterialTheme.colorScheme.background

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Yellow)
            .animateContentSize()
    ){
       
        Canvas(modifier = Modifier.fillMaxSize()){
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawCircle(
                color = backgroundColor,
                center = Offset(x = canvasWidth/circleAnimValue, y = canvasHeight/circleAnimValue ),
                radius = size.maxDimension
            )

        }
        
        AnimatedLogo(startAnimation = startAnimation, color = White)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(null)
}