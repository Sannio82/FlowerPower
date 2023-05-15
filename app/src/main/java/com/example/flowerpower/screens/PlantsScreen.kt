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
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowerpower.repo.StorageRepository
import com.example.flowerpower.ui.theme.*
import com.example.flowerpower.viewmodels.Plant
import com.example.flowerpower.views.CreateNewPlantView
import com.example.flowerpower.views.composables.PlantCard
import com.example.flowerpower.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantsScreen() {

    val context = LocalContext.current
    var plantsList by remember { mutableStateOf(listOf<Plant>()) }

    LaunchedEffect(plantsList) {
        StorageRepository.readDataFromFirestore(context) { plants ->
            plantsList = plants
        }
    }

    var isCreatePlantScreenOpen by remember { mutableStateOf(false) }

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
            Text(text = stringResource(id = R.string.my_plants),
                fontSize = 35.sp,
                fontFamily = vanillaCake,
                fontWeight = FontWeight(600),
                color = Blue
            )
            if (plantsList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.padding(bottom = 55.dp)
                ) {
                    items(plantsList) { plant ->
                        PlantCard(plant = plant)
                    }
                }
            } else {
                Text(stringResource(id = R.string.no_added_plants))
                TextButton(onClick = { isCreatePlantScreenOpen = true }) {
                   Text(text = stringResource(id = R.string.text_easy_to_add))
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
            Text("+", fontSize = 40.sp, fontWeight = FontWeight.Bold, fontFamily = vanillaCake, color = Blue)
        }
        if(isCreatePlantScreenOpen) {
            CreateNewPlantView(closeAction = { isCreatePlantScreenOpen = false })
        }
    }
}