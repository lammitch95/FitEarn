package com.example.fitearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.ui.theme.FitEarnTheme
import com.example.fitearn.utils.LoggedUser
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            FitEarnTheme {
                // Navigate to your main entry page
                AppNavigator()
            }
        }

        // Initialize AppCenter using the app secret from AppSecrets
       // val appSecret = AppSecrets.getAppCenterSecret(this)
       // AppCenter.start(application, appSecret, Analytics::class.java, Crashes::class.java)
    }

    override  fun onStop() {
        super.onStop()

        LoggedUser.loggedInUser?.let { user ->

            val appDatabase = AppDatabase.getDatabase(applicationContext)
            val userDao = appDatabase.userDao()

            lifecycleScope.launch {
                userDao.updateUser(user)
            }
        }


    }


}
