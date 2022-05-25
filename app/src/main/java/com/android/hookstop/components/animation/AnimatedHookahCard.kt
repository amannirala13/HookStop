package com.android.hookstop.components.animation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.hookstop.R
import com.android.hookstop.components.Buttons.Companion.RoundButton
import com.android.hookstop.data.Hookah
import com.android.hookstop.ui.theme.Black
import com.android.hookstop.ui.theme.BlackOverlay
import com.android.hookstop.ui.theme.White
import com.android.hookstop.ui.theme.WhiteOverlay
import com.squareup.picasso.Picasso
import java.lang.System.load

@Composable
fun AnimatedHookahCard(
    modifier: Modifier = Modifier,
    curveRadius: Dp = 24.dp,
    padding: Dp = 16.dp,
    onClick: () -> Unit = {},
    onButtonClick: () -> Unit = {},
    data: Hookah,
){
    Box(modifier = modifier
        .fillMaxWidth()
        .fillMaxSize(0.8f)
        .padding(padding)
        .clickable { onClick() }
        .clip(shape = RoundedCornerShape(curveRadius)),
        contentAlignment = Alignment.Center
    ){
        ParallaxBox(inverse = true, body = {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f)
                    .clip(shape = RoundedCornerShape(curveRadius)),
                painter = painterResource(id = R.drawable.img_hookah_1),
                contentScale = ContentScale.Crop,
                contentDescription = "logo")
        })
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(BlackOverlay)
            .fillMaxSize()){
            ParallaxBox(body = {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween) {
                    Column(modifier = Modifier
                        .fillMaxWidth()){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            RoundButton(
                                modifier = Modifier.padding(16.dp),
                                backgroundColor = if (isSystemInDarkTheme()) Black else White, icon = {
                                    Image(
                                        imageVector = Icons.Rounded.Place,
                                        colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) White else Black),
                                        contentDescription = "Place"
                                    )
                                })
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "${data.brand}",
                                color = White,
                                style = MaterialTheme.typography.h2,
                                fontWeight = FontWeight.Black,
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = data.about ?: "",
                                color = White,
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.Light,
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "$${data.rent}",
                                color = White,
                                style = MaterialTheme.typography.h3,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "/8 hour\non all days",
                                color = White,
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Start){
                        Spacer(modifier = Modifier.width(16.dp))
                        AnimatedRoundTextButton(icon = {
                            Image(
                                imageVector = Icons.Rounded.Check,
                                contentDescription = "Book") },
                            text = "Rent")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            })

        }
    }
}