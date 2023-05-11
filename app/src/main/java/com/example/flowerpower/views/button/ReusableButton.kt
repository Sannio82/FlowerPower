package com.example.flowerpower.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.flowerpower.ui.theme.robotoMedium

@Composable
fun ReusableButton(
    text: String,
    gradient: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        contentPadding = PaddingValues(),
    )
         {
             Box(
                 modifier = Modifier
                     .background(gradient)
                     .then(modifier),
                 contentAlignment = Alignment.Center
             ) {
        Text(
            text,
            color = Color.White,
            fontFamily = robotoMedium,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
             }
        }
    }
