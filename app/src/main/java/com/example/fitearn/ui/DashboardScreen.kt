package com.example.fitearn.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitearn.ui.DashboardScreenViewModel

@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: DashboardScreenViewModel
) {
    val user = viewModel.userState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF7B2CBF), Color(0xFF6A0DAD))
                )
            )
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            // Profile Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome, ${user?.firstName ?: "Guest"}",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Steps Today Section
            Text(
                text = "Steps Today",
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Card(
                modifier = Modifier.size(200.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color(0xFFEE9A00))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "${user?.stepsCount ?: 0}",
                        fontSize = 32.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Update Steps Button
            Button(onClick = { viewModel.updateSteps(1000) }) {
                Text("Add 1000 Steps")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Metrics Section (e.g., Badges, Coins, Weather)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                MetricCard(title = "Badges", value = "3")
                MetricCard(title = "Coins", value = "${user?.coinAmount ?: 0}")
                MetricCard(title = "Weather", value = "65°F")
            }
        }
    }
}

@Composable
fun MetricCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(Color(0xFF4CAF50))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = title, fontSize = 14.sp, color = Color.White)
            Text(text = value, fontSize = 16.sp, color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDashboardScreen() {
    val viewModel: DashboardScreenViewModel = viewModel()
    DashboardScreen(navController = rememberNavController(), viewModel = viewModel)
}

