// LeaderboardEntry.kt
package com.example.fitearn

data class LeaderboardEntry(
    val rank: Int,
    val username: String,
    val avatar: String = "", // Optional field for avatar
    val points: Int,
    val isCurrentUser: Boolean = false
)
