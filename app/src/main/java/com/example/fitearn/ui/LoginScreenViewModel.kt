package com.example.fitearn.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitearn.auth.Login
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.utils.LoggedUser
import com.example.fitearn.utils.ValidationUtils
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val appDatabase: AppDatabase): ViewModel() {

    companion object {
        fun provideFactory(appDatabase: AppDatabase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
                        return LoginScreenViewModel(appDatabase) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

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
                val validUser = appDatabase.userDao().login(emailState.value, passwordState.value)
                if (loginSuccess && validUser!=null) {
                    LoggedUser.setLoggedInUser(validUser)
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