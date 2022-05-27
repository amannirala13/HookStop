package com.android.hookstop.components.animation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.hookstop.ui.theme.*

@Composable
fun AnimatedTallButton(modifier:Modifier = Modifier,
                       onClick: ()-> Unit,
                       backgroundColor: Color = White,
                       icon: @Composable ()-> Unit){
    ParallaxBox(inverse = true, modifier= Modifier.padding(8.dp),body ={
        Box(modifier = modifier
            .clip(shape = CircleShape)
            .clickable(onClick = onClick)
            .background(backgroundColor)
            .padding(2.dp)
            .border(width = 7.dp,
                color = GrayOverlay,
                shape = CircleShape)
            .fillMaxHeight()
            ,
            contentAlignment = Alignment.Center
        ){
            Box(Modifier.padding(16.dp)){
                ParallaxBox(body = {icon()})
            }
        }
    })
}


/**
 *
 * @param modifier Modifier
 * @param backgroundColor Color
 * @param icon [@androidx.compose.runtime.Composable] Function0<Unit>
 * @param onClick Function0<Unit>
 */
@Composable
fun AnimatedRoundButton(modifier: Modifier = Modifier,
                        backgroundColor: Color = White,
                        icon: @Composable ()-> Unit,
                        onClick: ()->Unit = {}){
    Box(modifier = modifier
        .shadow(elevation = 12.dp,
            shape = RoundedCornerShape(24.dp)
        )
    ){
        Box(modifier = Modifier
            .background(
                shape = RoundedCornerShape(24.dp),
                color = backgroundColor
            )
            .clip(RoundedCornerShape(24.dp))
            .width(56.dp)
            .height(56.dp)
            .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ){
           ParallaxBox(travelFactorY = 0.5.dp , body = {icon()})
        }
    }
}

@Composable
fun AnimatedRoundTextButton(modifier: Modifier = Modifier,
                            backgroundColor: Color = White,
                            icon: @Composable ()-> Unit,
                            text: String,
                            textColor: Color = Black,
                            onClick: ()->Unit = {}){
    Box(modifier = modifier
        .shadow(elevation = 12.dp,
            shape = CircleShape
        )
    ){
        Box(modifier = Modifier
            .background(
                shape = CircleShape,
                color = backgroundColor
            )
            .clip(CircleShape)
            .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ){
            ParallaxBox(
                travelFactorY = 0.5.dp,
                body = {
                Row(modifier = Modifier.padding(16.dp)){
                    Spacer(modifier = Modifier.width(16.dp))
                    icon()
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        modifier = Modifier,
                        color = textColor,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.body1,
                        text = text)
                    Spacer(modifier = Modifier.width(16.dp))
                }

            })
        }
    }
}