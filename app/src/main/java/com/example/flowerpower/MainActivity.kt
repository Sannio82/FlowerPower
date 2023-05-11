package com.example.flowerpower

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowerpower.screens.CreateAccountScreen
import com.example.flowerpower.screens.LogInScreen
import com.example.flowerpower.screens.PlantsScreen
import com.example.flowerpower.screens.WelcomeScreen
import com.example.flowerpower.ui.theme.FlowerPowerTheme
import com.example.flowerpower.views.navigationbar.BottomNavItem
import com.example.flowerpower.views.navigationbar.NavigationBar

class MainActivity: ComponentActivity() {
        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
        @OptIn(ExperimentalMaterial3Api::class)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {

                FlowerPowerTheme {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            NavigationBar(
                                navController = navController,
                                items = listOf(
                                    BottomNavItem(
                                        route = "WelcomeScreen",
                                        icon = Icons.Default.Home,
                                        name = "Home"
                                ),
                                    BottomNavItem(
                                        route = "LogInScreen",
                                        icon = Icons.Default.Star,
                                        name = "Log in"
                                    ),
                                    BottomNavItem(
                                        route = "CreateAccountScreen",
                                        icon = Icons.Default.AccountBox,
                                        name = "Create account"
                                    ),
                                    BottomNavItem(
                                        route = "PlantsScreen",
                                        icon = Icons.Default.Phone,
                                        name = "My Plants"
                                    )
                            ), onItemClicked = {
                                navController.navigate(it.route)
                                }

                            )
                        }
                    ) {
                    Navigation(navController = navController)

                    }
                }
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "WelcomeScreen"
    ) {
        composable(route = "WelcomeScreen") { WelcomeScreen(navController) }
        composable(route = "LogInScreen") { LogInScreen(navController) }
        composable(route = "CreateAccountScreen") { CreateAccountScreen(navController) }
        composable(route = "PlantsScreen") { PlantsScreen() }
    }
}
