package com.android.hookstop.components.animation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.hookstop.components.utils.clamp
import kotlin.math.abs

/**
 * A box with parallax effect
 * @param modifier Modifier
 * @param body [@androidx.compose.runtime.Composable] Function0<Unit>
 * @param travelFactorX Dp
 * @param travelFactorY Dp
 * @param sensitivityFactorX Float
 * @param sensitivityFactorY Float
 * @param thresholdX Float
 * @param thresholdY Float
 * @param startAnimation Boolean
 */
@Composable
fun ParallaxBox(modifier: Modifier = Modifier,
                body: @Composable ()-> Unit,
                travelFactorX: Dp = 2.5.dp,
                travelFactorY: Dp = 1.dp,
                sensitivityFactorX: Float = 0.5f,
                sensitivityFactorY: Float = 0.5f,
                thresholdX: Float = 0.1f,
                thresholdY: Float = 0.1f,
                inverse: Boolean = false,
                startAnimation: Boolean = true ) {

    val sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    var sensorListenerAttached by remember {
        mutableStateOf(false)
    }

    var gyroAnimation by remember{
        mutableStateOf(listOf(0f,0f))
    }

    var previousGyroAnimation by remember {
        mutableStateOf(listOf(0f,0f))
    }

    val gyroAnimationDistanceX = with(LocalDensity.current){travelFactorX.toPx()}
    val gyroAnimationDistanceY = with(LocalDensity.current){travelFactorY.toPx()}

    val gyroX = animateFloatAsState(
        targetValue = if(startAnimation) gyroAnimation[0] else 0f,
        animationSpec = tween(durationMillis = 1, easing = LinearEasing)
    )

    val gyroY = animateFloatAsState(
        targetValue = if(startAnimation) gyroAnimation[1] else 0f,
        animationSpec = tween(durationMillis = 1, easing = LinearEasing)
    )

    gyroSensor?.let{
        if(!sensorListenerAttached){
            sensorListenerAttached =  sensorManager.registerListener(object: SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    if (event != null) {
                        val newX = (event.values[0] * sensitivityFactorX).clamp(-1f, 1f)
                        val newY = (event.values[1] * sensitivityFactorY).clamp(-1f, 1f)

                        val differenceX = abs(previousGyroAnimation[0] - newX)
                        val differenceY = abs(previousGyroAnimation[1] - newY)
                        val prevList: MutableList<Float> = previousGyroAnimation.toMutableList()
                        val gyroList: MutableList<Float> = gyroAnimation.toMutableList()
                        if(differenceX > thresholdX) {
                            prevList[0] = gyroList[0]
                            gyroList[0] = if(inverse) -newX else newX
                        }
                        if(differenceY > thresholdY) {
                            prevList[1] = gyroList[1]
                            gyroList[1] = if(inverse) newY else -newY
                        }

                        previousGyroAnimation = prevList
                        gyroAnimation = gyroList

                        Log.d("Sensor_log", gyroAnimation.toString())
                    }else{
                        Log.d("Sensor_log","Sensor missing")
                    }
                }override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
            }, gyroSensor, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }
    
    Box(modifier = modifier
        .graphicsLayer {
            if (startAnimation) {
                    translationX = (gyroAnimationDistanceX) * gyroX.value
                    translationY = (gyroAnimationDistanceY) * gyroY.value
            }
        }){
        body()
    }
}