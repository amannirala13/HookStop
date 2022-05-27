package com.android.hookstop.components.graphics

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.android.hookstop.R

@Composable
fun CoilImage(modifier: Modifier = Modifier, data: Any){
    Box(modifier = modifier.fillMaxSize()){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberAsyncImagePainter(
                onState = {
                          Log.d("Image_state", it.toString())
                },
            model = data),
            contentScale = ContentScale.Crop,
            contentDescription = "hookah")
    }
}