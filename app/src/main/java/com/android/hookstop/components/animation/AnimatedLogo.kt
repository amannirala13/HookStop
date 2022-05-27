package com.android.hookstop.components.animation

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.hookstop.R
import com.android.hookstop.ui.theme.White
import com.android.hookstop.ui.theme.Yellow
import com.android.hookstop.values.Duration

@Composable
fun AnimatedLogo(modifier: Modifier = Modifier,
                 color: Color = Yellow,
                 startAnimation: Boolean
                 ){
    val cartAnim = animateFloatAsState(targetValue = if (startAnimation) 0f else 1f,
        animationSpec = tween(durationMillis = Duration.SplashScreenCartAnimationDuration.value.toInt(), easing = LinearOutSlowInEasing)
    )

    val cartDistance = with(LocalDensity.current){20.dp.toPx()}

    val alphaAnim = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = Duration.SplashScreenAnimationDuration.value.toInt())
    )

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically){
        Image(
            modifier = Modifier.graphicsLayer {
                translationX = -cartAnim.value * cartDistance
                alpha = 1f - cartAnim.value
            },
            painter = painterResource(id = R.drawable.ic_hookah),
            colorFilter = ColorFilter.tint(color),
            contentDescription = "logo")
        Spacer(modifier = Modifier.width(8.dp))
        Text("HookStop",
            color = color,
            modifier = Modifier.alpha(alphaAnim.value),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold)

    }
}