package com.android.hookstop.values

sealed class Duration(val value: Long){
    object SplashScreenDuration: Duration(1300)
    object SplashScreenAnimationDuration: Duration(1000)
    object SplashScreenCartAnimationDuration: Duration(1600)
    object SplashScreenCircleAnimationDuration: Duration(3000)
    object TextFieldBaseAnimation: Duration(1000)
    object LoginInAnimation: Duration(2500)
    object LoginOutAnimation: Duration(700)

}
