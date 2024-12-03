package com.example.fitearn.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitearn.data.dao.UserDao
import com.example.fitearn.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardScreenViewModel(
    private val userDao: UserDao,
    private val email: String
) : ViewModel() {

    val userState = mutableStateOf<User?>(null)

    init {
        loadUserData()
    }

    // Load user data based on email
    private fun loadUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.checkIfEmailExists(email)
            userState.value = user
        }
    }

    // Update steps count
    fun updateSteps(newSteps: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userState.value
            if (user != null) {
                val updatedUser = user.copy(stepsCount = user.stepsCount + newSteps)
                userDao.updateUser(updatedUser)
                userState.value = updatedUser
            }
        }
    }
}
