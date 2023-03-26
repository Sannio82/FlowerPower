package com.example.flowerpower.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.flowerpower.ui.theme.Coral

@Preview
@Composable
fun PlantsScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Coral),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Välkommen till plantskärmen!",
            fontSize = 35.sp
            )

    }
}