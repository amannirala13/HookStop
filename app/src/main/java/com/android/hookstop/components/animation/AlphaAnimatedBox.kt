package com.android.hookstop.components.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
fun AlphaAnimatedBox(
    modifier: Modifier = Modifier,
    startAnimation: Boolean = false,
    content: @Composable ()-> Unit
){

    val alphaAnimation = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
    )

    Box(modifier = modifier,
        contentAlignment = Alignment.Center){
        Box(modifier = Modifier.alpha(alphaAnimation.value)){content()}
    }
}