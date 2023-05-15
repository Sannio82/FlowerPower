package com.example.flowerpower.views

import android.net.Uri
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
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
import com.example.flowerpower.views.button.GradientButton
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.flowerpower.repo.StorageRepository
import com.example.flowerpower.ui.theme.vanillaCake

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
                .fillMaxSize()
                .background(Beige),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = { closeAction() },
                    modifier = Modifier
                        .padding(top = 20.dp, end = 20.dp)
                        .align(Alignment.TopEnd)
                        .border(width = 2.dp, color = Blue, shape = CircleShape)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Blue
                    )
                }
            }
            Text(
                text = stringResource(id = R.string.add_new_plant),
                fontSize = 35.sp,
                fontFamily = vanillaCake,
                fontWeight = FontWeight(600),
                color = Blue
            )
            Spacer(modifier = Modifier.size(20.dp))
                IconButton(
                    modifier = Modifier.padding(bottom = 15.dp),
                    onClick = {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    })
                {
                    SelectedImage(selectedImageUri = selectedImageUri)
                }
            TextField(
                value = plantName,
                label = { Text(text = stringResource(id = R.string.plant_name), fontFamily = vanillaCake) },
                onValueChange = { newPlantName ->
                    plantName = newPlantName
                },
                )
            Spacer(modifier = Modifier.size(5.dp))
            TextField(
                value = description,
                label = { Text(text = stringResource(id = R.string.description), fontFamily = vanillaCake) },
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
                    text = stringResource(id = R.string.save),
                    onClick = {
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
                )
            }
        }
    }
}

@Composable
fun SelectedImage(selectedImageUri: Uri?) {
    selectedImageUri?.let {
        AsyncImage(
            model = it,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.3f)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
    } ?: run {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.3f),
            contentScale = ContentScale.Crop
        )
    }
}
