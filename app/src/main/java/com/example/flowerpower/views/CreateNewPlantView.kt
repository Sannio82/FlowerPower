package com.example.flowerpower.views

import android.net.Uri
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowerpower.R
import com.example.flowerpower.ui.theme.Beige
import com.example.flowerpower.ui.theme.Blue
import com.example.flowerpower.ui.theme.jambo
import com.example.flowerpower.views.button.GradientButton
import androidx.compose.foundation.Image
import com.example.flowerpower.repo.StorageRepository

@Composable
fun CreateNewPlantView(closeAction: () -> Unit) {

    var plantName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )

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
                    onClick = { closeAction() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .border(width = 2.dp, color = Blue, shape = CircleShape)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close"
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
                modifier = Modifier.padding(bottom = 15.dp),
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                })
            {
                Image(painter = painterResource(id = R.drawable.image), contentDescription = null )
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
                        selectedImageUri?.let {
                            StorageRepository.addDataToFirebase(
                                plantName,
                                description,
                                it,
                                context
                            )
                        }
                        closeAction()
                    }
                }
            }
        }
    }
}