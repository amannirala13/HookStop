package com.android.hookstop.components.animation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.hookstop.ui.theme.Black
import com.android.hookstop.ui.theme.GrayOverlay
import com.android.hookstop.ui.theme.White

@Composable
fun AnimatedSearchBar(modifier:Modifier = Modifier,
                      onClick: ()-> Unit,
                      isSearchActive: Boolean  = false,
                      backgroundColor: Color = White){
    ParallaxBox(inverse = true, modifier= Modifier.padding(8.dp),body ={
        Box(modifier = modifier
            .clip(shape = CircleShape)
            .clickable(onClick = onClick)
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(2.dp)
            .border(
                width = 7.dp,
                color = GrayOverlay,
                shape = CircleShape
            )
            .fillMaxHeight()
            ,
            contentAlignment = Alignment.Center
        ){
            Box(Modifier.padding(16.dp)){
                ParallaxBox(body = {
                    Row(modifier = Modifier.fillMaxSize()){
                        Image(
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp),
                            imageVector = Icons.Rounded.Search,
                            colorFilter = ColorFilter
                                .tint(color = if(isSystemInDarkTheme()) White else Black ),
                            contentDescription = "Search"
                        )
                    }
                })
            }
        }
    })
}

@Composable
@Preview
fun AnimatedSearchBarPreview(){
    AnimatedSearchBar(onClick = { /*TODO*/ })
}