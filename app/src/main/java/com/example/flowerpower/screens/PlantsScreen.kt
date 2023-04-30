package com.example.flowerpower.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowerpower.ui.theme.*
import com.example.flowerpower.viewmodels.plantList
import com.example.flowerpower.views.CreateNewPlantView
import com.example.flowerpower.views.composables.PlantCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantsScreen() {

    var isCreatePlantScreenOpen by remember { mutableStateOf(true) }

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
        contentAlignment = Alignment.BottomEnd
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

        FloatingActionButton(
        modifier = Modifier
            .padding(bottom = 70.dp, end = 25.dp),
            onClick = { isCreatePlantScreenOpen = true },
            contentColor = Color.Black,
            backgroundColor = FloatingButtonColor
            ) {
            Text("+", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        }
        if(isCreatePlantScreenOpen) {
            CreateNewPlantView()
        }
    }
}