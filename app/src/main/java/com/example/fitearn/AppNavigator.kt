package com.example.fitearn

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.ui.LoginPage
import com.example.fitearn.ui.RegistrationPage
import com.example.fitearn.ui.SplashScreen

@Composable
fun AppNavigator() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginPage(navController) }
        composable("dashboard") { DashboardPage(navController) }
        composable("registration") { RegistrationPage(navController) }
    }
}

@Composable
fun DashboardPage(navController: NavHostController) {
    // Content for the Dashboard page
}
