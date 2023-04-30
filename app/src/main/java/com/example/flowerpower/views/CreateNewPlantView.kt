package com.example.flowerpower.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowerpower.ui.theme.Beige
import com.example.flowerpower.ui.theme.Blue
import com.example.flowerpower.ui.theme.jambo
import com.example.flowerpower.views.button.GradientButton

@Composable
fun CreateNewPlantView() {

    var plantName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(20.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center,
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .fillMaxHeight(0.8f)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(Beige)
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Lägg till ny planta",
                fontSize = 35.sp,
                fontFamily = jambo,
                fontWeight = FontWeight(600),
                color = Blue
            )
            Spacer(modifier = Modifier.size(25.dp))
            IconButton(
                onClick = { /*TODO*/ })
            {
                Icon(
                    Icons.Default.Info,
                    contentDescription = "Add image"
                )
            }
            TextField(
                value = plantName,
                label = { Text(text = "Namn på planta") },
                placeholder = { Text(text = "Skriv namn på plantan") },
                onValueChange = { newPlantName ->
                    plantName = newPlantName
                },
                )
            Spacer(modifier = Modifier.size(25.dp))
            TextField(
                value = description,
                label = { Text(text = "Beskrivning") },
                placeholder = { Text(text = "Skriv namn på plantan") },
                onValueChange = { newDescription ->
                    description = newDescription
                })
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                GradientButton(
                    text = "Spara",
                ) { /*TODO skicka allt till firebase*/
                    println("Nu ska allt skickas iväg")
                }
            }
        }
    }
}