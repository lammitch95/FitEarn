package com.example.fitearn.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitearn.auth.UserDataManager
import com.example.fitearn.data.database.AppDatabase
import kotlinx.coroutines.launch

class UserInfoScreenViewModel(private val appDatabase: AppDatabase): ViewModel() {

    companion object {
        fun provideFactory(appDatabase: AppDatabase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(UserInfoScreenViewModel::class.java)) {
                        return UserInfoScreenViewModel(appDatabase) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    var dateOfBirthState = mutableStateOf("")
        private set
    var weightState = mutableStateOf("")
        private set
    var heightState = mutableStateOf("")
        private set
    var phoneNumberState = mutableStateOf("")
        private set

    var dateOfBirthError = mutableStateOf("")
        private set
    var weightError = mutableStateOf("")
        private set
    var heightError = mutableStateOf("")
        private set
    var phoneNumberError = mutableStateOf("")
        private set

    // Field change handlers
    fun onDateOfBirthChange(newDateOfBirth: String) {
        dateOfBirthState.value = newDateOfBirth

    }

    fun onWeightChange(newWeight: String) {
        weightState.value = newWeight
    }

    fun onHeightChange(newHeight: String) {
        heightState.value = newHeight
    }

    fun onPhoneNumberChange(newPhoneNumber: String) {
        phoneNumberState.value = newPhoneNumber
    }

    // Validation function
    fun validateFields(): Boolean {
        dateOfBirthError.value = if (dateOfBirthState.value.isEmpty()) "Date Of Birth Field Cannot Be Empty!" else ""
        weightError.value = if (weightState.value.isEmpty()) "Weight Field Cannot Be Empty!" else ""
        heightError.value = if (heightState.value.isEmpty()) "Height Field Cannot Be Empty!" else ""
        phoneNumberError.value = if (phoneNumberState.value.isEmpty()) "Phone Number Field Cannot Be Empty!" else ""

        return dateOfBirthError.value.isEmpty() &&
                weightError.value.isEmpty() &&
                heightError.value.isEmpty() &&
                phoneNumberError.value.isEmpty()
    }

    // Save user info to UserDataManager
    fun saveUserInfo(onSaveSuccess: () -> Unit) {
        if (validateFields()) {
            viewModelScope.launch {
                UserDataManager.dateOfBirth = dateOfBirthState.value
                UserDataManager.weight = weightState.value
                UserDataManager.phoneNumber = phoneNumberState.value
                onSaveSuccess()
            }
        }
    }
}