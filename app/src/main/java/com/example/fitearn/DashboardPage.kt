package com.example.fitearn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DashboardPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF833AB4), Color(0xFFFD1D1D), Color(0xFFFEDB5B))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to FitEarn",
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Button for Leaderboard
        FeatureButton(
            text = "Leaderboard",
            onClick = { navController.navigate("leaderboard") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button for Shop
        FeatureButton(
            text = "Avatar Shop",
            onClick = { navController.navigate("shop") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
        )
    }
}

@Composable
fun FeatureButton(text: String, onClick: () -> Unit, colors: ButtonColors) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(56.dp)
    ) {
        Button(
            onClick = onClick,
            colors = colors,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 18.sp, color = Color.White)
            )
        }
    }
}
