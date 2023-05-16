package com.example.flowerpower.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowerpower.R
import com.example.flowerpower.ui.theme.Blue
import com.example.flowerpower.ui.theme.Coral
import com.example.flowerpower.ui.theme.Yellow
import com.example.flowerpower.ui.theme.vanillaCake
import com.example.flowerpower.viewmodels.AuthViewModel
import com.example.flowerpower.views.button.ButtonBack
import com.example.flowerpower.views.button.GradientButton
import com.example.flowerpower.views.composables.FlowerPowerField

@Composable
fun CreateAccountScreen(navController: NavController) {
    val viewModel: AuthViewModel = viewModel()

    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val toastMessage by viewModel.toastMessage.collectAsState()

    if (toastMessage != null) {
        LaunchedEffect(toastMessage) {
            viewModel.showToastMessage(context, 0)
            viewModel.showToastMessage(null, null) // Reset the toast message to avoid showing it again
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
        Box(){
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logga_one),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
            ButtonBack(
                modifier = Modifier.padding(35.dp),
                onClick = { navController.navigate("WelcomeScreen") }
            )
        Column(
            Modifier
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            SignUpHeader()
            SignUpFields(
                userName, password,
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
                            viewModel.showToastMessage(context, R.string.enter_email)
                        }
                        password.isBlank() -> {
                            viewModel.showToastMessage(context, R.string.enter_password)
                        }
                        else -> {
                            viewModel.createAccount(context, userName, password)
                        }
                    }
                }
            )
        }
    }
    }
}

@Composable
fun SignUpHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.create_account), fontSize = 36.sp, fontWeight = FontWeight.ExtraBold, fontFamily = vanillaCake, color = Blue)
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
            label = stringResource(id = R.string.username),
            placeholder = stringResource(id = R.string.enter_email),
            onValueChange = onUsernameChange,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )
        Spacer(Modifier.height(8.dp))

        FlowerPowerField(value = password,
            label = stringResource(id = R.string.password),
            placeholder = stringResource(id = R.string.enter_password),
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
        GradientButton(
            text = stringResource(id = R.string.sign_up),
            onClick = onSignUpClick
        )
    }
}







