package com.example.flowerpower.views.composables

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flowerpower.viewmodels.Plant
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.flowerpower.ui.theme.*

@Composable
fun PlantCard(plant: Plant) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Beige)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(plant.title, fontWeight = FontWeight.Bold, fontSize = 26.sp, fontFamily = jambo, color = Blue)
                Image(painterResource(
                    id = plant.image),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .fillMaxWidth()

                        .height(150.dp),
                    )
                Text(plant.description, fontFamily = jambo, color = Blue, modifier = Modifier.padding(top = 8.dp))
            }
        }
}