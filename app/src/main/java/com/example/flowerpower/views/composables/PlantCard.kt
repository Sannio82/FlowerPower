package com.example.flowerpower.views.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flowerpower.viewmodels.Plant
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.flowerpower.ui.theme.*
import com.example.flowerpower.R
import com.example.flowerpower.repo.StorageRepository

@Composable
fun PlantCard(plant: Plant) {

    val context = LocalContext.current

    Card(
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
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
                        .padding(top = 20.dp)
                ){
                    Image(
                        painter = rememberAsyncImagePainter(model = plant.imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(35.dp))
                            .fillMaxWidth()
                    )
                    //TODO make image clickable to show popup to reset timer if plant is watered
                    Image(
                        painterResource(id = R.drawable.waterplants),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    )
                }
                Text(plant.title, fontWeight = FontWeight.Bold, fontSize = 26.sp, fontFamily = vanillaCake, color = Blue,
                    modifier = Modifier.padding(15.dp))
                Text(plant.description, fontFamily = vanillaCake, color = Blue, modifier = Modifier.padding(top = 8.dp))
            }
            IconButton(
                onClick = {
                    StorageRepository.deleteData(plant.id, context)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete),
                tint = Blue
            )
            }
        }
    }
}