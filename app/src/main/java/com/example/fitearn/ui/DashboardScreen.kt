package com.example.fitearn.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun DashboardScreen(navController: NavHostController) {
    // Content for the Dashboard page
    Text(
        text = "This is dashboard page"
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardScreen(){
    DashboardScreen(navController = rememberNavController())
}