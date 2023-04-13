package com.example.flowerpower.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowerpower.ui.theme.Blue
import com.example.flowerpower.ui.theme.Coral
import com.example.flowerpower.ui.theme.Yellow
import com.example.flowerpower.ui.theme.jambo
import com.example.flowerpower.viewmodels.Plant
import com.example.flowerpower.viewmodels.plantList
import com.example.flowerpower.views.composables.PlantCard

@Composable
fun PlantsScreen() {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Mina plantor!",
                fontSize = 35.sp,
                fontFamily = jambo,
                fontWeight = FontWeight(600),
                color = Blue
            )
            LazyColumn(
                modifier = Modifier.padding(bottom = 55.dp)
            ) {
                items(plantList) {plant ->
                    PlantCard(plant = plant)
                }
            }
        }
    }
}