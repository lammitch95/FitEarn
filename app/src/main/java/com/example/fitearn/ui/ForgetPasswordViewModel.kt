package com.example.fitearn.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ForgotPasswordViewModel : ViewModel() {

    val emailState = mutableStateOf("")
    val messageState = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    // Simulate password reset (replace with actual backend logic, e.g., Firebase)
    fun resetPassword() {
        if (emailState.value.isEmpty()) {
            messageState.value = "Please enter a valid email."
            return
        }

        viewModelScope.launch {
            isLoading.value = true

            // Simulate a network delay (Replace with actual reset password API call)
            kotlinx.coroutines.delay(2000)

            if (emailState.value.contains("@")) { // Simple email validation
                messageState.value = "A reset link has been sent to ${emailState.value}."
            } else {
                messageState.value = "Invalid email address."
            }

            isLoading.value = false
        }
    }
}
