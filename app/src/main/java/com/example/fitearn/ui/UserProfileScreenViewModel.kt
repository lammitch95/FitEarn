package com.example.fitearn.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitearn.auth.fetchUserFromAuth
import com.example.fitearn.data.database.AppDatabase
import kotlinx.coroutines.launch

class UserProfileScreenViewModel(private val appDatabase: AppDatabase): ViewModel() {

    companion object {
        fun provideFactory(appDatabase: AppDatabase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(UserProfileScreenViewModel::class.java)) {
                        return UserProfileScreenViewModel(appDatabase) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    var firstNameState = mutableStateOf("")
        private set
    var lastNameState = mutableStateOf("")
        private set
    var phoneNumberState = mutableStateOf("")
        private set
    var emailState = mutableStateOf("")
        private set
    var weightState = mutableStateOf("")
        private set
    var dateOfBirthState = mutableStateOf("")
        private set

    var errorState = mutableStateOf("")
        private set

    fun fetchUserProfile(onError: (String) -> Unit) {
        viewModelScope.launch {
            fetchUserFromAuth(
                onSuccess = { user ->
                    firstNameState.value = user.firstName
                    lastNameState.value = user.lastName
                    phoneNumberState.value = user.phoneNumber
                    emailState.value = user.email
                    weightState.value = user.weight
                    dateOfBirthState.value = user.dateOfBirth
                },
                onError = { error ->
                    errorState.value = error.message ?: "Unknown error occurred"
                    onError(errorState.value)
                }
            )
        }
    }
}
