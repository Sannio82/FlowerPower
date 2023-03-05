package com.example.flowerpower.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavController
import com.example.flowerpower.ui.theme.Coral
import com.example.flowerpower.ui.theme.Yellow

@Composable
fun CreateAccountScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Coral,
                        Yellow
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        "Hej hej allihopa!"
    }
}