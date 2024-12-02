package com.example.fitearn.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitearn.auth.Registration
import com.example.fitearn.utils.ValidationUtils

class RegistrationScreenViewModel: ViewModel(){

    var firstNameState = mutableStateOf("")
        private set
    var lastNameState = mutableStateOf("")
        private set
    var emailState = mutableStateOf("")
        private set
    var passwordState = mutableStateOf("")
        private set
    var termCondition = mutableStateOf(false)
        private set
    var passwordVisiblity = mutableStateOf(false)
        private set
    var isLoading = mutableStateOf(false)
        private set
    var registrationError = mutableStateOf("")
        private set


    val firstNameError: String get() = if (firstNameState.value.isEmpty()) "First name cannot be empty" else ""
    val lastNameError: String get() = if (lastNameState.value.isEmpty()) "Last name cannot be empty" else ""
    val emailError: String get() = if (emailState.value.isEmpty()) "Email cannot be empty" else ValidationUtils.validateEmail(emailState.value)
    val passwordError: String get() = if (passwordState.value.isEmpty()) "Password cannot be empty" else ValidationUtils.validatePassword(passwordState.value)

    fun onFirstNameChange(value: String){ firstNameState.value = value }
    fun onLastNameChange(value: String){lastNameState.value = value}
    fun onEmailChange(value: String){emailState.value = value}
    fun onPasswordChange(value: String){passwordState.value = value}
    fun onTermConditionChange(value: Boolean){termCondition.value = value}
    fun onPasswordVisibility(){passwordVisiblity.value = !passwordVisiblity.value}
    fun onIsLoadingChange(value: Boolean){isLoading.value = value}
    fun onRegistrationError(value: String){registrationError.value = value}

    suspend fun registerUser() : Boolean {
        if (firstNameError.isEmpty() && lastNameError.isEmpty() && emailError.isEmpty() &&
            passwordError.isEmpty() && termCondition.value) {

            isLoading.value = true
            registrationError.value = ""
            val success = Registration.registerUser(
                firstName = firstNameState.value,
                lastName = lastNameState.value,
                email = emailState.value,
                password = passwordState.value
            )
            isLoading.value = false
            return success
        } else {
            registrationError.value = "Please fill all fields correctly and accept the Terms & Conditions."
            return false
        }
    }

}