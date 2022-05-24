package com.android.hookstop.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.hookstop.ui.theme.*
import com.android.hookstop.values.Duration

class Inputs{
    companion object{

        @Composable
        fun AnimatedTextField(
            label: String,
            baseOffSet: Float = 5f,
            modifier: Modifier,
            textModifier: Modifier = Modifier,
            keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle: TextStyle = TextStyle(fontWeight = FontWeight.Normal),
            leadingIcon: @Composable (() -> Unit)?,
            trailingIcon: @Composable (() -> Unit)?
        ){

            var startBaseAnimation by remember {
                mutableStateOf(false)
            }
            val baseAnim = animateFloatAsState(targetValue = if(startBaseAnimation) 1f else baseOffSet,
                animationSpec = tween(durationMillis = Duration.TextFieldBaseAnimation.value.toInt())
            )

            var text by remember { mutableStateOf("")}
            Column(modifier = modifier, horizontalAlignment = Alignment.End
            ){
                TextField(value = text,
                    onValueChange = { text = it},
                    modifier = textModifier.onFocusChanged { focus ->
                        startBaseAnimation = focus.hasFocus
                    },
                    keyboardOptions = keyboardOptions,
                    textStyle = textStyle,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = if(isSystemInDarkTheme()) White else Black,
                        backgroundColor = Transparent,
                        cursorColor = Yellow,
                        focusedLabelColor = Yellow,
                        focusedIndicatorColor = Transparent,
                        unfocusedIndicatorColor = GrayLight
                    ),
                    label = {Text(label)},
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon
                )
                TextFieldBase(baseOffSet, baseAnim.value)
            }

        }

        @Composable
        fun AnimatedPasswordTextField(
            label: String,
            baseOffSet: Float = 5f,
            modifier: Modifier,
            textModifier: Modifier = Modifier,
            keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle: TextStyle = TextStyle(fontWeight = FontWeight.Normal),
            leadingIcon: @Composable (() -> Unit)?,
            trailingIcon: @Composable (() -> Unit)?
        ){

            var startBaseAnimation by remember {
                mutableStateOf(false)
            }
            val baseAnim = animateFloatAsState(targetValue = if(startBaseAnimation) 1f else baseOffSet,
                animationSpec = tween(durationMillis = Duration.TextFieldBaseAnimation.value.toInt())
            )

            var text by remember { mutableStateOf("")}
            Column(modifier = modifier, horizontalAlignment = Alignment.End
            ){
                TextField(value = text,
                    onValueChange = { text = it},
                    modifier = textModifier.onFocusChanged { focus ->
                        startBaseAnimation = focus.hasFocus
                    },
                    keyboardOptions = keyboardOptions,
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = textStyle,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = if(isSystemInDarkTheme()) White else Black,
                        backgroundColor = Transparent,
                        cursorColor = Yellow,
                        focusedLabelColor = Yellow,
                        focusedIndicatorColor = Transparent,
                        unfocusedIndicatorColor = GrayLight
                    ),
                    label = {Text(label)},
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon
                )
                TextFieldBase(baseOffSet, baseAnim.value)
            }

        }

        @Composable
        fun TextFieldBase(baseOffSet: Float, baseAnim: Float){
            Canvas(modifier = Modifier
                .fillMaxWidth()){

                val width = size.width
                val height = size.width

                drawLine(color = Yellow,
                    start = Offset(x = width/baseOffSet, y = 0f),
                    end = Offset(x = width/baseAnim, y =0f),
                    strokeWidth = 7f
                )
            }
        }

    }
}