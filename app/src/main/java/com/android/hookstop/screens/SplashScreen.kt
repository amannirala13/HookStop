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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.android.hookstop.ui.theme.Blue
import com.android.hookstop.ui.theme.BlueLight
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController?) {

    var startAnimation by remember { mutableStateOf(false) }
    var startCircleAnimation by remember { mutableStateOf(false)}

    val circleAnim = animateFloatAsState(targetValue = if (startCircleAnimation) 2f else 0f,
        animationSpec = tween(durationMillis = Duration.SplashScreenCircleAnimationDuration.value.toInt())
    )

    val cartAnim = animateFloatAsState(targetValue = if (startAnimation) 0f else 1f,
        animationSpec = tween(durationMillis = Duration.SplashScreenCartAnimationDuration.value.toInt(), easing = LinearOutSlowInEasing)
    )
    val cartDistance = with(LocalDensity.current){20.dp.toPx()}
    val circleDistance = with(LocalDensity.current){100.dp.toPx()}

    val alphaAnim = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = Duration.SplashScreenAnimationDuration.value.toInt())
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

    Splash(alphaAnim.value, cartDistance, cartAnim.value, circleDistance, circleAnim.value)
}

@Composable
fun Splash(alpha: Float,
           cartDistance: Float,
           cartAnimValue: Float,
           circleDistance: Float,
           circleAnimValue: Float){
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize()
    ){
       
        Canvas(modifier = Modifier.fillMaxSize()){
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawCircle(
                color = BlueLight,
                center = Offset(x = canvasWidth/circleAnimValue, y = canvasHeight/circleAnimValue ),
                radius = size.maxDimension
            )

        }
        
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                modifier = Modifier.graphicsLayer {
                    this.translationX = -cartAnimValue * cartDistance
                },
                imageVector = Icons.Outlined.ShoppingCart,
                colorFilter = ColorFilter.tint(if(isSystemInDarkTheme()) BlueLight else Blue),
                contentDescription = "logo")
            Spacer(modifier = Modifier.width(8.dp))
            Text("HookStop",
                color = if(isSystemInDarkTheme()) BlueLight else Blue,
                modifier = Modifier.alpha(alpha),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(null)
}