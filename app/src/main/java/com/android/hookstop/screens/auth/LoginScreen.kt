package com.android.hookstop.screens.auth

import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.hookstop.R
import com.android.hookstop.components.Buttons
import com.android.hookstop.components.Inputs
import com.android.hookstop.components.animation.ParallaxBox
import com.android.hookstop.nav.Screen
import com.android.hookstop.ui.theme.*
import com.android.hookstop.values.Duration
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController?){
    val context =  LocalContext.current as? Activity
    val coroutine = rememberCoroutineScope()

    var startAnim by remember {
        mutableStateOf(true)
    }
    var gyroAnimationEnabled by remember {
        mutableStateOf(false)
    }

    val animTransitionDistance = with(LocalDensity.current){60.dp.toPx()}

    val anim = animateFloatAsState(
        targetValue = if(startAnim) 1f else 0f,
        animationSpec = tween(durationMillis = Duration.LoginInAnimation.value.toInt())
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)){

        LaunchedEffect(key1 = true){
            startAnim = false
            delay(Duration.LoginInAnimation.value)
            gyroAnimationEnabled = true
        }

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            ){
            Spacer(modifier = Modifier.height(72.dp))
            Row(modifier = Modifier.fillMaxWidth()){
                Spacer(modifier = Modifier.width(64.dp))
                Box(modifier = Modifier.graphicsLayer {
                    translationX = -animTransitionDistance * anim.value
                }){
                    ParallaxBox(modifier = Modifier,
                        body = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_hookah),
                                contentDescription = "logo",
                                colorFilter = ColorFilter
                                    .tint(Yellow)
                            )
                        })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()){
                Spacer(modifier = Modifier.width(64.dp))
                Text(text = "Hello!",
                    modifier = Modifier.graphicsLayer {
                        translationY = animTransitionDistance * anim.value },
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
//                    color = Yellow,
                    style = MaterialTheme.typography.headlineLarge)
            }
            Row(modifier = Modifier.fillMaxWidth()){
                Spacer(modifier = Modifier.width(64.dp))
                Text(text = "let's get started",
                    modifier = Modifier.graphicsLayer {
                        translationY = animTransitionDistance * anim.value },
                    color = Gray,
                    style = MaterialTheme.typography.headlineLarge)
            }
            Spacer(modifier = Modifier.height(64.dp))
            Column(modifier = Modifier.fillMaxWidth()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        translationX = animTransitionDistance * anim.value
                    }
                ){
                    Inputs.AnimatedTextField(
                        modifier = Modifier,
                        textModifier = Modifier,
                        baseOffSet = 3f,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        label = "Phone",
                        leadingIcon = {
                                      Image(
                                          imageVector = Icons.Rounded.Phone,
                                          colorFilter = ColorFilter.tint(Yellow),
                                          contentDescription = "Phone")
                        },
                        trailingIcon = null
                    )
                }
                Spacer(modifier = Modifier.height(48.dp))
                ParallaxBox(modifier = Modifier,
                    startAnimation = gyroAnimationEnabled,
                    body = {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                            horizontalArrangement = Arrangement.End){
                            Buttons.GoButton(modifier = Modifier.graphicsLayer {
                                translationX = -animTransitionDistance * anim.value
                            }){
                                Toast.makeText(context, "Sending OTP", Toast.LENGTH_SHORT).show()
                                coroutine.launch {
                                    gyroAnimationEnabled = false
                                    startAnim = true
                                    delay(Duration.LoginOutAnimation.value)
                                    navController?.navigate(Screen.MainActivity.route)
                                }

                            }
                        }
                })
            }

        }
    }

}

@Composable
@Preview
fun LoginScreenPreview(){
    LoginScreen(null)
}