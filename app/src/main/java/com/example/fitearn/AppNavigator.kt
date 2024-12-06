package com.example.fitearn


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.ui.AvatarCollectionScreen
import com.example.fitearn.ui.DashboardScreen
import com.example.fitearn.ui.ForgotPasswordScreen
import com.example.fitearn.ui.StepTracker
import com.example.fitearn.ui.LoginPage
import com.example.fitearn.ui.RegistrationPage
import com.example.fitearn.ui.ShopScreen
import com.example.fitearn.ui.SplashScreen
import com.example.fitearn.ui.UserInfoScreen
import com.example.fitearn.ui.UserProfile

@Composable
fun AppNavigator() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "steptracker") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginPage(navController) }
        composable("dashboard") { StepTracker(navController) }//testing purposes we switch back to the dashboard screen later
        composable("registration") { RegistrationPage(navController) }
        composable("userinfo") { UserInfoScreen(navController) }
        composable("userprofile") { UserProfile(navController) }
        composable("steptracker") { StepTracker(navController) }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("shop"){ ShopScreen(navController)}
        composable("avatarCollection") { AvatarCollectionScreen(navController) }

    }
}


