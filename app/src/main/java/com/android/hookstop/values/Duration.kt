package com.android.hookstop.values

sealed class Duration(val value: Long){
    object SplashScreenDuration: Duration(1300)
    object SplashScreenAnimationDuration: Duration(1000)
    object SplashScreenCartAnimationDuration: Duration(3000)
    object SplashScreenCircleAnimationDuration: Duration(3000)
}
