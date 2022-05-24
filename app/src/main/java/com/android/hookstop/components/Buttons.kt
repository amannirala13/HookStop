package com.android.hookstop.components

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
            Box(modifier = Modifier
                .padding(12.dp)
                .height(32.dp)
                .width(32.dp)){
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .background(RedSurface)

                ){
                    IconButton(
                        onClick = onClickAction,){
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            tint = Red,
                            contentDescription = "Close")
                    }
                }
            }
        }

        @Composable
        fun GoButton(modifier:Modifier = Modifier, onClick: ()-> Unit){
            Box(modifier = modifier
                .shadow(elevation = 12.dp,
                shape = RoundedCornerShape(24.dp)
                )
            ){
                Box(modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(24.dp),
                        color = Yellow
                    )
                    .clip(RoundedCornerShape(24.dp))
                    .width(56.dp)
                    .height(56.dp)
                    .clickable(onClick = onClick),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp),
                        imageVector = Icons.Rounded.KeyboardArrowRight,
                        colorFilter = ColorFilter.tint(White),
                        contentDescription = "login")
                }
            }
        }

    }

    @Preview (showBackground = true)
    @Composable
    fun ButtonsPreview(){
        Column{
            Row{
                SearchButton {}
                ExitButton {}
                GoButton {}
            }
        }
    }
}
