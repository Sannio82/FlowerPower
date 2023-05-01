package com.example.flowerpower.views

import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowerpower.repo.addDataToFirebase
import com.example.flowerpower.ui.theme.Beige
import com.example.flowerpower.ui.theme.Blue
import com.example.flowerpower.ui.theme.jambo
import com.example.flowerpower.views.button.GradientButton

@Composable
fun CreateNewPlantView() {

    var plantName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center,
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize(0.92f)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(Beige)
                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .border(width = 2.dp, color = Blue, shape = CircleShape)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Add image"
                    )
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
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
                    .padding(top = 40.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                GradientButton(
                    text = "Spara",
                ) {
                    if (TextUtils.isEmpty(plantName)) {
                        Toast.makeText(context, "Please enter plant name", Toast.LENGTH_SHORT).show()
                    } else if (TextUtils.isEmpty(description)) {
                        Toast.makeText(context, "Please enter description", Toast.LENGTH_SHORT).show()
                    }  else {
                        addDataToFirebase(
                            plantName,
                            description,
                            context
                        )
                    }
                    println("Nu ska allt skickas iväg")
                }
            }
        }
    }
}