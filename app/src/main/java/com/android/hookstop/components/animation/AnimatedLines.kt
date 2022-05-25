package com.android.hookstop.components.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.hookstop.ui.theme.Yellow

@Composable
fun AnimatedVerticalLine(
    modifier: Modifier = Modifier,
    width: Dp = 3.dp,
    color: Color = Yellow,
    startAnimation: Boolean = false,
    duration: Int = 1500
    ){

    val animation = animateFloatAsState(targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = duration, easing = FastOutSlowInEasing))

    val widthPx = with(LocalDensity.current){width.toPx()}

    Box(modifier = modifier){
        Canvas(modifier = Modifier
            .width(width)
            .fillMaxHeight()){
            val canvasHeight = size.height
            val canvasWidth = size.width

            drawLine(
                color = color,
                strokeWidth = widthPx,
                start = Offset(x = canvasWidth/2, y = 0f),
                end = Offset(x = canvasWidth/2, y = canvasHeight * animation.value)
            )
        }
    }
}