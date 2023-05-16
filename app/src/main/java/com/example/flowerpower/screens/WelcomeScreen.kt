package com.example.flowerpower.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flowerpower.ui.theme.*
import com.example.flowerpower.views.button.GradientButton
import com.example.flowerpower.R

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
        Box() {
            Image(
                painter = painterResource(id = R.drawable.logga_one),
                contentDescription = null,
                Modifier.size(250.dp)
            )
        }
        Spacer(modifier = Modifier.size(100.dp))
        GradientButton(
            text = stringResource(id = R.string.sign_in),
            onClick = { navController.navigate("LogInScreen") }
        )
        Spacer(modifier = Modifier.size(20.dp))
        GradientButton(
            text = stringResource(R.string.create_account),
            onClick = { navController.navigate("CreateAccountScreen") }
            )
    }
    }
}