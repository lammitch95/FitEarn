package com.example.fitearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.ui.theme.FitEarnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitEarnTheme {
                // This will navigate to your main entry page
                AppNavigator()

            }
        }
    }
}

