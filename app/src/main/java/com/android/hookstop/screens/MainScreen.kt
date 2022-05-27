package com.android.hookstop.screens

import android.graphics.Paint
import android.widget.Space
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.hookstop.components.Buttons.Companion.RoundButton
import com.android.hookstop.components.animation.*
import com.android.hookstop.data.Hookah
import com.android.hookstop.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(navController: NavHostController){

    var startAnimation by remember{
        mutableStateOf(false)
    }

    var startPostAnimation by remember{
        mutableStateOf(false)
    }

    var lazyListState = rememberLazyListState()

    val dummyData by remember{
        mutableStateOf(listOf<Hookah>(
            Hookah("Maya", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya2", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
            Hookah("Maya3", "MYA QT is a Piece of Art. This 14 inch MYA QT  is Packaged in a Wire Basket","Aman Nirala", "Pune", 10.0, 4.5),
        ))
    }

    val pagerState = rememberPagerState()

    val movementDistance = with(LocalDensity.current){300.dp.toPx()}

    val alphaAnimation = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f,
    animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing))

    val alphaPostAnimation1 = animateFloatAsState(targetValue = if (startPostAnimation) 0f else 1f,
        animationSpec = tween(durationMillis = 1600, easing = FastOutSlowInEasing))

    val alphaPostAnimation2 = animateFloatAsState(targetValue = if (startPostAnimation) 0f else 1f,
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing))

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
           Row(modifier = Modifier
               .fillMaxWidth()
               .padding(16.dp),
                horizontalArrangement =  Arrangement.Center){
                AnimatedLogo(modifier = Modifier,
                    startAnimation = startAnimation,
                    color = if(isSystemInDarkTheme()) White else Black)
            }
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
                        AlphaAnimatedBox(startAnimation = startAnimation) {
                            Text(
                                text = "Find\nThe Perfect\nHookah",
                                style = MaterialTheme.typography.h4,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                })

                AnimatedTallButton(
                    modifier = Modifier.alpha(alphaAnimation.value),
                    onClick = {
                        startAnimation = false
                        startPostAnimation = false
                    },
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
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .alpha(alphaAnimation.value)){
                    Box(modifier = Modifier,
                        contentAlignment = Alignment.Center){
                        Column{
                            AnimatedHookahCard(data = dummyData[0])
                        }
                    }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)){
                Row(modifier = Modifier.graphicsLayer {
                    translationX = - movementDistance * alphaPostAnimation1.value
                }){
                    Spacer(modifier = Modifier.width(24.dp))
                    ParallaxBox(body = {
                        RoundButton(
                            onClick={},
                            backgroundColor = DeepWhite,
                            icon = {
                                Image(imageVector = Icons.Rounded.Menu,
                                    colorFilter = ColorFilter.tint(Black),
                                    contentDescription = "More")
                            })
                    })
                }
                Spacer(modifier = Modifier.width(16.dp))
                Row(modifier = Modifier.graphicsLayer {
                    translationX = - movementDistance * alphaPostAnimation2.value
                }){
                    ParallaxBox(body = { RoundButton(
                        onClick={},
                        backgroundColor = DeepWhite,
                        icon = {
                            Image(imageVector = Icons.Rounded.ShoppingCart,
                                colorFilter = ColorFilter.tint(Black),
                                contentDescription = "Orders")
                        })
                    })
                }
                Spacer(modifier = Modifier.width(16.dp))

                AnimatedHorizontalLine(modifier = Modifier.clip(RoundedCornerShape(topStart = 100.dp,
                    bottomStart = 100.dp)).fillMaxSize(),
                    canvasModifier =Modifier.clip(RoundedCornerShape(topStart = 100.dp,
                        bottomStart = 100.dp)),
                    height = 7.dp,
                    inverse = true,
                    startAnimation = startAnimation)
            }

        }


        LaunchedEffect(key1 = true){
            startAnimation = true
            delay(1200)
            startPostAnimation = true
        }
    }
}