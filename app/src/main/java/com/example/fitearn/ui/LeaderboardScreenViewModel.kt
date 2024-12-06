package com.example.fitearn.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LeaderboardScreenViewModel(private val appDatabase: AppDatabase) : ViewModel() {
    val leaderboardData = MutableStateFlow<List<User>>(emptyList())

    init {
        fetchLeaderboardData()
    }

    private fun fetchLeaderboardData() {
        viewModelScope.launch {
            leaderboardData.value = appDatabase.userDao().getAllUsers()
        }
    }

    companion object {
        fun provideFactory(appDatabase: AppDatabase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(LeaderboardScreenViewModel::class.java)) {
                        return LeaderboardScreenViewModel(appDatabase) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}
