package com.example.flowerpower.views.composables

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.flowerpower.R
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlantCard(plant: Plant, nextWateringDate: LocalDate) {
    val daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), nextWateringDate).toInt()
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
                .padding(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ){
                Image(painterResource(
                    id = plant.image),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                )
                if (daysLeft <= 0) {
                    //TODO make image clickable to show popup to reset timer if plant is watered
                    Image(
                        painterResource(id = R.drawable.waterplants),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    )
                    Text(
                        text = "Needs water",
                        fontFamily = jambo,
                        color = Blue,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp)
                    )
                }
            }
            Text(plant.title, fontWeight = FontWeight.Bold, fontSize = 26.sp, fontFamily = jambo, color = Blue,
                modifier = Modifier.padding(15.dp))
            Text(plant.description, fontFamily = jambo, color = Blue, modifier = Modifier.padding(top = 8.dp))
        }
    }
}