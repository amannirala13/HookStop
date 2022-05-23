package com.android.hookstop.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.hookstop.ui.theme.*

class Buttons{
    companion object{
        @Composable
        fun SearchButton(onClickAction: ()-> Unit){
                Box{
                        IconButton(onClick = onClickAction) {
                            Icon(imageVector = Icons.Rounded.Search,
                                tint = if(isSystemInDarkTheme()) White else Black ,
                                contentDescription = "Search")
                        }
                }
        }

        @Composable
        fun ExitButton(onClickAction: () -> Unit){
            Box(modifier = Modifier.width(36.dp).height(36.dp)){
                IconButton(
                    onClick = onClickAction,){
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        tint = Red,
                        contentDescription = "Close")
                }
            }
        }

    }
}
