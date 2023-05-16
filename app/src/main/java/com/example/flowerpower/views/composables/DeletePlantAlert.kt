package com.example.flowerpower.views.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun DeletePlantAlert(
    onDismissDialog: () -> Unit,
    onDeletePlant: () -> Unit
) {

    AlertDialog(
        onDismissRequest = { onDismissDialog() },
        title = {
            Text(text = "Delete Plant")
        },
        text = {
            Text(text = "Are you sure you want to delete this plant?")
        },
        confirmButton = {
            Button(
                onClick = {
                    onDeletePlant()
                },
                colors = ButtonDefaults.buttonColors(Color.DarkGray)
            ) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismissDialog()}
            ) {
                Text(text = "No")
            }
        }
    )
}