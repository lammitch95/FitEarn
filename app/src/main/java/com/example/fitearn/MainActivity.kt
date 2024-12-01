package com.example.fitearn
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fitearn.ui.theme.FitEarnTheme
import com.google.firebase.FirebaseApp

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

        // Request Activity Recognition permission for step tracking
        requestActivityRecognitionPermission()
    }

    private val requestPermissionLauncher = registerForActivityResult(
        RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("Permission", "ACTIVITY_RECOGNITION granted")
        } else {
            Log.e("Permission", "ACTIVITY_RECOGNITION denied")
        }
    }

    // Call this function to request the permission
    private fun requestActivityRecognitionPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("Permission", "ACTIVITY_RECOGNITION already granted")
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        }
    }


    companion object {
        private const val ACTIVITY_RECOGNITION_REQUEST_CODE = 1
    }
}
