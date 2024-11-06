package com.example.fitearn

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LeaderboardScreen(leaderboardEntries: List<LeaderboardEntry>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF833AB4), Color(0xFFFD1D1D), Color(0xFFFEDB5B))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Leaderboard",
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
            modifier = Modifier.padding(8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(leaderboardEntries) { entry ->
                LeaderboardEntryRow(entry)
            }
        }
    }
}

@Composable
fun LeaderboardEntryRow(entry: LeaderboardEntry) {
    val isTopRank = entry.rank <= 3
    val infiniteTransition = rememberInfiniteTransition()
    val pulse = infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.medium)
            .background(
                color = if (entry.isCurrentUser) Color(0xFFFFD700) else Color.White,
                shape = MaterialTheme.shapes.medium
            )
            .padding(8.dp)
            .scale(if (isTopRank) pulse.value else 1f), // Scale effect for top ranks
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Rank circle with animation for top ranks
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = when (entry.rank) {
                        1 -> Color(0xFFFFD700)
                        2 -> Color(0xFFC0C0C0)
                        3 -> Color(0xFFCD7F32)
                        else -> Color(0xFF6200EE)
                    },
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${entry.rank}",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // User information
        Column {
            Text(
                text = entry.username,
                fontSize = 18.sp,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "${entry.points} pts",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}
