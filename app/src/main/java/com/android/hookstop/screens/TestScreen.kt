package com.android.hookstop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.android.hookstop.R
import com.android.hookstop.components.animation.ParallaxBox
import com.android.hookstop.components.graphics.CoilImage
import com.android.hookstop.data.Hookah

@Composable
fun TestScreen(navController: NavHostController) {
    val c = rememberCoroutineScope()

    val dummyData by remember{
        mutableStateOf(listOf(
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


    Box(modifier = Modifier.fillMaxSize()){
        LazyRow(modifier = Modifier){
            dummyData.forEach{ data ->
                item{
                   Box(modifier = Modifier
                       .fillMaxSize()
                       .padding(8.dp)){
                       CoilImage(modifier = Modifier.fillParentMaxSize(), data = R.drawable.img_hookah_1)
                       /*ParallaxBox(body = { */ Box(modifier = Modifier.fillMaxSize()){
                           Text(data.brand?:"null")
                       }/*})*/
                   }
                }
            }
        }
    }


}