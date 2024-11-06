package com.example.fitearn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

im
import com.example.fitearn.ui.theme.FitEarnTheme

@Composable
fun UserInfo(navController: NavHostController) {
    var userDOB by remember { mutableStateOf("") }
    var userWeight by remember { mutableStateOf("") }
    var userHeight by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }

    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color(0xFF8A2BE2),
                        1.0f to Color(0xFF0000FF)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "User Profile",
            color = Color(0xFFF9972C),
            fontSize = 60.sp,
            modifier = androidx.compose.ui.Modifier
                .padding(top = 200.dp)
                .width(319.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(80.dp))

        TextField(
            value = userDOB,
            onValueChange = { userDOB = it },
            placeholder = { Text(text = "Date Of Birth", fontSize = 26.sp) },
            modifier = androidx.compose.ui.Modifier
                .width(313.dp)
                .height(65.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(30.dp))

        TextField(
            value = userWeight,
            onValueChange = { userWeight = it },
            placeholder = { Text(text = "Weight", fontSize = 26.sp) },
            modifier = androidx.compose.ui.Modifier
                .width(313.dp)
                .height(65.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(30.dp))

        TextField(
            value = userHeight,
            onValueChange = { userHeight = it },
            placeholder = { Text(text = "Height", fontSize = 26.sp) },
            modifier = androidx.compose.ui.Modifier
                .width(313.dp)
                .height(65.dp),
            colors = TextFieldDefaults.colors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(30.dp))

        TextField(
            value = userPhone,
            onValueChange = { userPhone = it },
            placeholder = { Text(text = "Phone Number", fontSize = 26.sp) },
            modifier = androidx.compose.ui.Modifier
                .width(313.dp)
                .height(65.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(70.dp))

        Button(
            onClick = { /* TODO: Handle button click */ },
            modifier = androidx.compose.ui.Modifier
                .width(300.dp)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(text = "Let's Get Started", fontSize = 30.sp, color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserInfoPage() {
    FitEarnTheme {
        UserInfo(navController = rememberNavController())
    }
}