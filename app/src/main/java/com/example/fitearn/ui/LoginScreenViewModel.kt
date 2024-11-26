package com.example.fitearn.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitearn.auth.Login
import com.example.fitearn.utils.ValidationUtils
import kotlinx.coroutines.launch

class LoginScreenViewModel(): ViewModel() {
    var emailState = mutableStateOf("")
        private set
    var passwordState = mutableStateOf("")
        private set
    var passwordVisibility = mutableStateOf(false)
        private set
    var loginError = mutableStateOf("")
        private set
    fun onEmailChange(newEmail: String) {
        emailState.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        passwordState.value = newPassword
    }

    fun togglePasswordVisibility() {
        passwordVisibility.value = !passwordVisibility.value
    }

    fun login(onLoginSuccess: () -> Unit) {
        val emailError = ValidationUtils.validateEmail(emailState.value)
        val passwordError = ValidationUtils.validatePassword(passwordState.value)

        if (emailError.isEmpty() && passwordError.isEmpty()) {
            viewModelScope.launch {
                val loginSuccess = Login.loginUser(emailState.value, passwordState.value)
                if (loginSuccess) {
                    onLoginSuccess()
                } else {
                    loginError.value = "Login failed. Please check your credentials."
                }
            }
        } else {
            loginError.value = emailError.ifEmpty { passwordError }
        }
    }
}