package com.example.fitearn.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fitearn.ui.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    viewModel: ForgotPasswordViewModel = viewModel()
) {
    val email by viewModel.emailState
    val message by viewModel.messageState
    val isLoading by viewModel.isLoading

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(201, 1, 254), Color(0, 76, 249))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Forgot Password",
            color = Color.White,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = { viewModel.emailState.value = it },
            placeholder = {
                Text("Enter your email", color = Color.White)
            },
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Button(
                onClick = { viewModel.resetPassword() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.size(200.dp, 50.dp)
            ) {
                Text(
                    text = "Reset Password",
                    color = Color(0, 76, 249),
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = message,
            color = if (message.startsWith("A reset")) Color.Green else Color.Red,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextButton(
            onClick = { navController.popBackStack() }
        ) {
            Text(
                text = "Back to Login",
                color = Color(255, 152, 0, 255)
            )
        }
    }
}
