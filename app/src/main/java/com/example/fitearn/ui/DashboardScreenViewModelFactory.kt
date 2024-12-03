package com.example.fitearn.ui

import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import com.example.fitearn.data.dao.UserDao

    class DashboardScreenViewModelFactory(
        private val userDao: UserDao,
        private val email: String
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DashboardScreenViewModel::class.java)) {
                return DashboardScreenViewModel(userDao, email) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

