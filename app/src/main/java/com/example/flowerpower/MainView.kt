package com.example.flowerpower

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowerpower.screens.CreateAccountScreen
import com.example.flowerpower.screens.LogInScreen
import com.example.flowerpower.screens.PlantsScreen
import com.example.flowerpower.screens.WelcomeScreen
import com.example.flowerpower.ui.theme.FlowerPowerTheme

@Composable
fun MainView() {
    val navController = rememberNavController()

    FlowerPowerTheme() {
        Scaffold { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
            )
        }
    }
}

@Composable
fun Content(
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "LogInScreen"
    ) {
        composable(route = "WelcomeScreen") { WelcomeScreen(navController) }
        composable(route = "LogInScreen") { LogInScreen(navController) }
        composable(route = "CreateAccountScreen") { CreateAccountScreen(navController)}
        composable(route = "PlantsScreen") { PlantsScreen()}

    }
}

