package com.example.fitearn.utils

import com.example.fitearn.model.User

object LoggedUser {
    var loggedInUser: User? = null
        private set

    fun setLoggedInUser(value: User?){
        loggedInUser = value
    }
}