package com.example.fitearn.utils

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.model.User
import kotlinx.coroutines.launch
import java.text.DecimalFormat

object LoggedUser {
    var loggedInUser: User? = null
        private set

    fun setLoggedInUser(value: User?){
        if(value != null){
            loggedInUser = value
        }else{
            Log.d("Login","Error with User Data")
        }

    }

    fun updateUserInfo(
        dateOfBirth: String,
        weight: String,
        height: String,
        phoneNum: String
    ):Boolean{

        loggedInUser?.apply {
            this.hasUserInfo = true
            this.dateOfBirth = dateOfBirth
            this.weight = weight
            this.height = height
            this.phoneNum = phoneNum
            return true

        }
        return false
    }

    @SuppressLint("DefaultLocale")
    fun formatCoins(coins: Int): String {
        return when {
            coins >= 1_000_000_000_000 -> String.format("%.2fT", coins / 1_000_000_000_000.0)
            coins >= 1_000_000_000 -> String.format("%.2fB", coins / 1_000_000_000.0)
            coins >= 1_000_000 -> String.format("%.2fM", coins / 1_000_000.0)
            coins >= 1_000 -> String.format("%.2fk", coins / 1_000.0)
            else -> coins.toString()
        }
    }

    fun formatCurrency(dollars: Double): String {
        val formattedDollar = DecimalFormat("#,###.00").format(dollars)
        return when {
            dollars >= 1_000_000_000_000 -> "%.2fT".format(dollars / 1_000_000_000_000)
            dollars >= 1_000_000_000 -> "%.2fB".format(dollars / 1_000_000_000)
            dollars >= 1_000_000 -> "%.2fM".format(dollars / 1_000_000)
            dollars >= 1_000 -> "%.2fk".format(dollars / 1_000)
            else -> formattedDollar
        }
    }
}