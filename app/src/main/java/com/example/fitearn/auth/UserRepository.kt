package com.example.fitearn.auth

import com.google.firebase.auth.FirebaseAuth

data class UserRepository(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val weight: String = "",
    val dateOfBirth: String = ""
)

fun fetchUserFromAuth(
    onSuccess: (UserRepository) -> Unit,
    onError: (Exception) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    if (currentUser != null) {

        val fullName = currentUser.displayName ?: ""
        val email = currentUser.email ?: ""

        val nameParts = fullName.split(" ")
        val firstName = nameParts.getOrNull(0) ?: ""
        val lastName = nameParts.getOrNull(1) ?: ""

        val userRepository = UserRepository(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = UserDataManager.phoneNumber,
            email = email,
            weight = UserDataManager.weight,
            dateOfBirth = UserDataManager.dateOfBirth
        )

        onSuccess(userRepository)
    } else {
        onError(Exception("User is not authenticated"))
    }
}