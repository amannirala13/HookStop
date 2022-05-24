package com.android.hookstop.components

import android.graphics.fonts.FontStyle
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.hookstop.ui.theme.HookStopTheme

/**
 *
 * @param title String
 * @param secondaryTitle String?
 * @param actionComponent [@androidx.compose.runtime.Composable] Function0<Unit>
 */
@Composable
fun AppBar(title:String, secondaryTitle: String?, actionComponent: @Composable ()->Unit) {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxWidth()){
            Row(modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column( modifier = Modifier.weight(3f)){
                    Text(text = title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                        )
                    secondaryTitle?.let{
                        Text(text = it, style = MaterialTheme.typography.bodySmall,)
                    }
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd){
                    actionComponent()
                }

            }
        }
}

@Preview
@Composable
fun HeadingPrev(){
    AppBar("Welcome Aman","Good morningfskldhjwoh wreoiuh vsdfjkhgjkshveiuwoh"){
        Buttons.SearchButton{}
    }
}