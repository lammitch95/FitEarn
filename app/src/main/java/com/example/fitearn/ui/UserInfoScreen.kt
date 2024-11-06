package com.example.fitearn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.ui.theme.FitEarnTheme

@Composable
fun UserInfoScreen(navController: NavController) {
    var dateOfBirth by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

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
        Spacer(modifier = Modifier.padding(vertical = 80.dp))

        Text(
            text = "User Info",
            color = Color(255, 152, 0, 255),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        TextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Date of Birth", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        TextField(
            value = weight,
            onValueChange = { weight = it },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Weight", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        TextField(
            value = height,
            onValueChange = { height = it },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Height", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            placeholder = { Text("Phone Number", color = Color.White, fontSize = 20.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(255, 152, 0, 255),
                unfocusedIndicatorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.padding(30.dp))

        Button(
            onClick = {
                // Handle "Let's Get Started" action
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier.size(200.dp, 50.dp)
        ) {
            Text(
                text = "Let's Get Started",
                color = Color(0, 76, 249),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserInfoScreen() {
    FitEarnTheme {
        UserInfoScreen(navController = rememberNavController())
    }
}
