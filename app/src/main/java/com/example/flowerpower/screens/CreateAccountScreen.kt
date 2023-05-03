package com.example.flowerpower.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowerpower.ui.theme.Blue
import com.example.flowerpower.ui.theme.Coral
import com.example.flowerpower.ui.theme.Yellow
import com.example.flowerpower.ui.theme.jambo
import com.example.flowerpower.viewmodels.AuthViewModel
import com.example.flowerpower.viewmodels.UserLoginStatus
import com.example.flowerpower.views.composables.FlowerPowerField

@Composable
fun CreateAccountScreen(navController: NavController)
{
    val viewModel: AuthViewModel = viewModel()

    val localContext = LocalContext.current

    var userName by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val loginStatus by viewModel.userLoginStatus.collectAsState()

    var showFailedDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = loginStatus ) {
        when(loginStatus) {
            is UserLoginStatus.Failure -> {
                localContext.showToast("Unable to login")
                println("Can not login!!!")
                showFailedDialog = true
            }
            UserLoginStatus.Successful -> {
                localContext.showToast("Login successful")
                navController.navigate("PlantsScreen")
            }
            null -> {

            }
        }
    }

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
        contentAlignment = Alignment.Center)
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
                .alpha(0.6f)
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(Color.White)
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            SignUpHeader(navController = navController)
            SignUpFields(userName, password,
                onUsernameChange = {
                    userName = it
                },
                onPasswordChange = {
                    password = it
                },
            )
            SignUpFooter(
                onSignUpClick = {
                    when {
                        userName.isBlank() -> {
                            //Use error field for this
                            localContext.showToast("Enter your username")
                        }
                        password.isBlank() -> {
                            localContext.showToast("Enter your password")
                        } else -> {
                        viewModel.createAccount(userName,password)
                    }
                    }
                }
            )
        }
    }

    if(showFailedDialog) {
        //Alert Dialog
    }
}

@Composable
fun SignUpHeader(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(
                onClick = { navController.navigate("WelcomeScreen")},
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .border(width = 2.dp, color = Blue, shape = CircleShape)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Close"
                )
            }
        }
        Spacer(modifier = Modifier.size(25.dp))
        Text(text = "CreateAccount!", fontFamily = jambo, color = Blue, fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun SignUpFields(username: String, password: String,
                onUsernameChange: (String) -> Unit,
                onPasswordChange: (String) -> Unit,
) {
    Column() {
        FlowerPowerField(
            value = username,
            label = "Username",
            placeholder = "Enter your email address",
            onValueChange = onUsernameChange,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )
        Spacer(Modifier.height(8.dp))

        FlowerPowerField(value = password,
            label = "Password",
            placeholder = "Enter your password",
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Go)
        )
    }
}

@Composable
fun SignUpFooter(
    onSignUpClick: () -> Unit
) {
    Column() {
        Button(onClick = onSignUpClick, modifier = Modifier.fillMaxWidth()) {
            Text("Sign up", fontFamily = jambo, color = Blue,)
        }
    }
}

fun Context.showSignUpToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}





