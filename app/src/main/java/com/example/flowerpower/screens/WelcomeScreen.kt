package com.example.flowerpower.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flowerpower.ui.theme.*
import com.example.flowerpower.views.buttons.GradientButton

@Composable
fun WelcomeScreen(
    navController: NavController
) {
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
        contentAlignment = Alignment.Center
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.8f)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 50.dp),
            text = "FLOWER POWER",
            fontSize = 50.sp,
            fontFamily = jambo,
            fontWeight = FontWeight(600),
            color = Blue
        )
        Spacer(modifier = Modifier.size(25.dp))
        Box() {
            Icon(
                Icons.Filled.Phone,
                tint = Blue,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(250.dp)
            )
        }
        Spacer(modifier = Modifier.size(60.dp))
        GradientButton(
            text = "Sign in",
            onClick = {navController.navigate("LogInScreen") }
        )
        Spacer(modifier = Modifier.size(20.dp))
        GradientButton(
            text = "Create account",
            onClick = { navController.navigate("CreateAccountScreen")}
        )
    }
    }
}