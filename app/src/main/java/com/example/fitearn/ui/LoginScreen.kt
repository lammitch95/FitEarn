// LoginPage
package com.example.fitearn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.R
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.ui.theme.FitEarnTheme
import com.example.fitearn.utils.LoggedUser


@Composable
fun LoginPage(navController: NavHostController) {

    val context = LocalContext.current
    val appDatabase = remember { AppDatabase.getDatabase(context) }
    val loginViewModel: LoginScreenViewModel = viewModel(
        factory = LoginScreenViewModel.provideFactory(appDatabase)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(201, 1, 254),
                        Color(0, 76, 249)
                    )
                )
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.padding(vertical = 100.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sign up",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {
                        navController.navigate("registration")
                    }
            )

            Text(
                text = "Sign in",
                color = Color(255, 152, 0, 255),
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 50.dp))

        TextField(
            value = loginViewModel.emailState.value,
            onValueChange = { loginViewModel.onEmailChange(it)},
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = {
                Text(
                    text = "Email",
                    color = Color.White,
                    fontSize = 20.sp
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = loginViewModel.passwordState.value,
            onValueChange = { loginViewModel.onPasswordChange(it)},
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = {
                Text(
                    text = "Password",
                    color = Color.White,
                    fontSize = 20.sp
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            trailingIcon = {
                IconButton(
                    onClick = { loginViewModel.togglePasswordVisibility()}
                ) {
                    val iconChange =
                        painterResource(id = if (loginViewModel.passwordVisibility.value) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24)
                    Icon(
                        painter = iconChange,
                        contentDescription = if (loginViewModel.passwordVisibility.value) "Show Password" else "Hide Password",
                        tint = Color.White
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            visualTransformation = if (loginViewModel.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
        )

        if (loginViewModel.loginError.value.isNotEmpty()) {
            Text(
                text = loginViewModel.loginError.value,
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Forgot Password",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("forgot_password")
                    },
                color = Color(255, 152, 0, 255)
            )
        }

        Spacer(modifier = Modifier.padding(15.dp))

        Button(
            onClick = {

                loginViewModel.login {
                    if(LoggedUser.loggedInUser?.hasUserInfo == true){
                        navController.navigate("dashboard")
                    }else{
                        navController.navigate("userinfo")
                    }

                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
            ),
            modifier = Modifier.size(150.dp, 50.dp)
        ) {
            Text(
                text = "Submit",
                color = Color(0, 76, 249),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginPage() {
    FitEarnTheme {
        LoginPage(navController = rememberNavController())
    }
}
