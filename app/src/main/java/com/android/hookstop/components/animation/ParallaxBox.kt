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
import kotlinx.coroutines.delay
import java.lang.Math.round
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * A box with parallax effect
 *
 * Travel in Y axis is a bit buggy!
 *
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
                travelFactorX: Dp = 3.dp,
                travelFactorY: Dp = 2.dp,
                sensitivityFactorX: Float = 0.5f,
                sensitivityFactorY: Float = 0.5f,
                thresholdX: Float = 0.5f,
                thresholdY: Float = 0.5f,
                inverse: Boolean = false,
                autoStart: Boolean = true,
                startAnimation: Boolean = false ) {

    val sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    var startAnim by remember {
        mutableStateOf(startAnimation)
    }
    var sensorListenerAttached by remember {
        mutableStateOf(false)
    }
    var gyroAnimation by remember{
        mutableStateOf(listOf(0f,0f))
    }
    var startPosition by remember {
            mutableStateOf(listOf(0f,0f))
    }
    var isCalibrated by remember {
        mutableStateOf(false)
    }
    var previousGyroAnimation by remember {
        mutableStateOf(listOf(0f,0f))
    }
    val gyroAnimationDistanceX = with(LocalDensity.current){travelFactorX.toPx()}
    val gyroAnimationDistanceY = with(LocalDensity.current){travelFactorY.toPx()}

    val gyroX = animateFloatAsState(
        targetValue = if(startAnim) gyroAnimation[0] else 0f,
        animationSpec = tween(durationMillis = 80, easing = LinearEasing)
    )

    val gyroY = animateFloatAsState(
        targetValue = if(startAnim) gyroAnimation[1] else 0f,
        animationSpec = tween(durationMillis = 80, easing = LinearEasing)
    )

    gyroSensor?.let{
        if(!sensorListenerAttached){
            sensorListenerAttached =  sensorManager.registerListener(object: SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    if (event != null) {
                        if(!isCalibrated){
                            Log.d("Initial", listOf(event.values[0], event.values[1]).toString())
                            startPosition = listOf(event.values[0], event.values[1])
                            previousGyroAnimation = listOf(0f,0f)
                            isCalibrated = true
                        }
                        val newX = -(startPosition[0] - event.values[0]  * sensitivityFactorX).clamp(-5f, 5f)
                        val newY = -(startPosition[1] - event.values[1]  * sensitivityFactorY).clamp(-1f, 1f)

                        val differenceX = abs(previousGyroAnimation[0] - newX).roundToInt()
                        val differenceY = abs(previousGyroAnimation[1] - newY).roundToInt()
                        val prevList: MutableList<Float> = previousGyroAnimation.toMutableList()
                        val gyroList: MutableList<Float> = gyroAnimation.toMutableList()
                        if(differenceX > thresholdX) {
                            prevList[0] = if(inverse) newX else -newX
                            gyroList[0] = if(inverse) newX else -newX
                        }
                        if(differenceY > thresholdY) {
                            prevList[1] = if(inverse) -newY else newY
                            gyroList[1] = if(inverse) -newY else newY
                        }

                        if(differenceX > thresholdX || differenceY > thresholdY){
                            previousGyroAnimation = prevList
                            gyroAnimation = gyroList
                        }

                        Log.d("Sensor_log", gyroAnimation.toString())
                    }else{
                        Log.d("Sensor_log","Sensor missing")
                    }
                }override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
            }, gyroSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    Box(modifier = modifier
        .graphicsLayer {
            if (startAnim && isCalibrated) {
                Log.d("Initial", "gyroX")
                translationX = (gyroAnimationDistanceX) * gyroX.value
                translationY = (gyroAnimationDistanceY) * gyroY.value
            }
        }){
        body()
    }

    LaunchedEffect(key1 = true){
        if(autoStart && !startAnim){
            delay(3000)
            startAnim = true
        }

    }
}