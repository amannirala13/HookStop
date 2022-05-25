package com.android.hookstop.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.hookstop.components.animation.*
import com.android.hookstop.data.Hookah
import com.android.hookstop.ui.theme.Black
import com.android.hookstop.ui.theme.White
import com.android.hookstop.ui.theme.Yellow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainActivity(navController: NavHostController){

    var startAnimation by remember{
        mutableStateOf(false)
    }

    val dummyData by remember{
        mutableStateOf(listOf<Hookah>(
            Hookah("Maya", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya2", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
        ))
    }

    val pagerState = rememberPagerState()

    val titleAlphaAnimation = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f,
    animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing))

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            ParallaxBox(body = { Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =  Arrangement.Center){
                AnimatedLogo(modifier = Modifier,
                    startAnimation = startAnimation,
                    color = if(isSystemInDarkTheme()) White else Black)
            }})
            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween){
                ParallaxBox(body = {
                    Row{
                        Spacer(modifier = Modifier.width(24.dp))
                        AnimatedVerticalLine(modifier = Modifier.clip(CircleShape),
                            width = 7.dp,
                            startAnimation = startAnimation)
                        Spacer(modifier = Modifier.width(32.dp))
                        Text(
                            modifier = Modifier.alpha(alpha = titleAlphaAnimation.value),
                            text = "Find\nThe Perfect\nHookah",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Black
                        )
                    }
                })
                AnimatedTallButton(
                    onClick = {},
                    icon = {
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
                    )
            }

            Row(modifier = Modifier.fillMaxWidth().alpha(titleAlphaAnimation.value)){
                HorizontalPager(count = dummyData.size, state = pagerState) { page ->
                    Box(modifier = Modifier
                        .fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        AnimatedHookahCard(data = dummyData[page])
                    }
                }

               /* LazyRow(modifier = Modifier.fillMaxWidth(0.5f)){
                    items(dummyData.size){ position->
                        AnimatedHookahCard(data = dummyData[position])
                    }
                }*/
            }

        }


        LaunchedEffect(key1 = true){
            startAnimation = true
        }
    }
}