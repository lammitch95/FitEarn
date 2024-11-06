package com.example.fitearn

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.DashboardPage
import com.example.fitearn.LeaderboardScreen
import com.example.fitearn.ShopScreen
import com.example.fitearn.LoginPage // Ensure this matches your Login composable file location
import com.example.fitearn.LeaderboardEntry
import com.example.fitearn.ShopItem

@Composable
fun AppNavigator() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginPage(navController) } // Corrected to LoginPage
        composable("dashboard") { DashboardPage(navController) }
        composable("registration") { RegistrationPage(navController) }

        composable("leaderboard") {
            LeaderboardScreen(leaderboardEntries = sampleLeaderboardEntries())
        }
        composable("shop") {
            ShopScreen(shopItems = sampleShopItems(), onPurchase = { /* Handle purchase */ })
        }
    }
}
@Composable
fun DashboardPage(navController: NavHostController) {

}

// Sample data for testing purposes
fun sampleLeaderboardEntries() = listOf(
    LeaderboardEntry(rank = 1, username = "User1", avatar = "", points = 500, isCurrentUser = true),
    LeaderboardEntry(rank = 2, username = "User2", avatar = "", points = 450),
)

fun sampleShopItems() = listOf(
    ShopItem(id = 1, name = "Item1", price = 150, image = ""),
    ShopItem(id = 2, name = "Item2", price = 200, image = "", isPurchased = true),
)
