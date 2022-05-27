package com.android.hookstop.components.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
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
    duration: Int = 1500,
    inverse: Boolean = false
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
            val startOffset: Offset
            val endOffset: Offset
            if(inverse){
                startOffset = Offset(x = canvasWidth/2, y = canvasHeight)
                endOffset = Offset(x = canvasWidth/2, y = canvasHeight - (canvasHeight * animation.value))
            }else{
                startOffset = Offset(x = canvasWidth/2, y = 0f)
                endOffset = Offset(x = canvasWidth/2, y = canvasHeight * animation.value)
            }

            drawLine(
                color = color,
                strokeWidth = widthPx,
                start = startOffset,
                end = endOffset
            )
        }
    }
}

@Composable
fun AnimatedHorizontalLine(
    modifier: Modifier = Modifier,
    canvasModifier: Modifier = Modifier,
    height: Dp = 3.dp,
    color: Color = Yellow,
    startAnimation: Boolean = false,
    duration: Int = 1500,
    inverse:Boolean = false
){

    val animation = animateFloatAsState(targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = duration, easing = FastOutSlowInEasing))

    val widthPx = with(LocalDensity.current){height.toPx()}

    Box(modifier = modifier, contentAlignment = Alignment.Center){
        Canvas(modifier = canvasModifier
            .height(height)
            .fillMaxWidth()){
            val canvasHeight = size.height
            val canvasWidth = size.width

            val startOffset: Offset
            val endOffset: Offset

            if(inverse){
                startOffset = Offset(x = canvasWidth, y = canvasHeight/2)
                endOffset = Offset(x = canvasWidth - (canvasWidth* animation.value), y = canvasHeight/2)
            }else{
                startOffset =  Offset(x = 0f, y = canvasHeight/2)
                endOffset = Offset(x = canvasWidth * animation.value, y = canvasHeight/2)
            }

            drawLine(
                color = color,
                strokeWidth = widthPx,
                start = startOffset,
                end = endOffset
            )
        }
    }
}