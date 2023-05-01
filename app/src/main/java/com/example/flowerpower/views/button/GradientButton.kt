package com.example.flowerpower.views.button

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.flowerpower.ui.theme.Blue
import com.example.flowerpower.ui.theme.LightBlue
import com.example.flowerpower.views.ReusableButton

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
) {
    val gradient = Brush.horizontalGradient(listOf(LightBlue, Blue))
    Column(
        modifier = Modifier
            .size(width = 290.dp, height = 50.dp)
            .clip(shape = RoundedCornerShape(30.dp)),
    ){
        ReusableButton(
            text = text,
            onClick = onClick,
            gradient = gradient,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}