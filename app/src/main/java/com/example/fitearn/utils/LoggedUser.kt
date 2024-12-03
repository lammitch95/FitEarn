package com.example.fitearn.utils

import com.example.fitearn.model.User

object LoggedUser {
    var loggedInUser: User? = null
        private set

    fun setLoggedInUser(value: User?){
        loggedInUser = value
    }

    fun updateUserInfo(
        dateOfBirth: String,
        weight: String,
        height: String,
        phoneNum: String
    ):Boolean{
        if(loggedInUser != null){
            loggedInUser!!.hasUserInfo = true
            loggedInUser!!.dateOfBirth = dateOfBirth
            loggedInUser!!.weight = weight
            loggedInUser!!.height = height
            loggedInUser!!.phoneNum = phoneNum
            return true
        }

        return false
    }


}