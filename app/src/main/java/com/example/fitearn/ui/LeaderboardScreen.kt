
package com.example.fitearn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavHostController
import com.example.fitearn.R
import com.example.fitearn.data.database.AppDatabase




@Composable
fun LeaderboardScreen(
    navController: NavHostController,
    appDatabase: AppDatabase
) {
    val viewModel: LeaderboardScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = LeaderboardScreenViewModel.provideFactory(appDatabase)
    )
    val leaderboardData = viewModel.leaderboardData.collectAsState()

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
        // Header for the leaderboard
        Text(
            text = "Leaderboard",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // this is the items for leaderboard items
        leaderboardData.value.forEachIndexed { index, user ->
            LeaderboardItem(
                rank = index + 1,
                username = "${user.firstName} ${user.lastName}",
                coins = user.coinAmount,
                backgroundColor = when (index) {
                    0 -> Color(0xFFFFD700) // Gold
                    1 -> Color(0xFFC0C0C0) // Silver
                    2 -> Color(0xFFCD7F32) // Bronze
                    else -> Color(0xFFFFE4B2) // Orange for others
                },
                medalIcon = when (index) {
                    0 -> R.drawable.ic_gold_medal
                    1 -> R.drawable.ic_silver_medal
                    2 -> R.drawable.ic_bronze_medal
                    else -> null
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController = navController)
    }
}

@Composable
fun LeaderboardItem(
    rank: Int,
    username: String,
    coins: Int,
    backgroundColor: Color,
    medalIcon: Int? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (medalIcon != null) {
            Icon(
                painter = painterResource(id = medalIcon),
                contentDescription = "Medal Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
        } else {
            Text(
                text = rank.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
        }


        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = username.first().toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Username
        Text(
            text = username,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))


        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.coins_icon),
                contentDescription = "Coin Icon",
                tint = Color.Yellow,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = coins.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}