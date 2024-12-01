package com.example.fitearn.ui

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.ui.theme.FitEarnTheme
import com.example.fitearn.utils.StepTracker

@Composable
fun DashboardPage(navController: NavHostController) {

    //Member Variables
    val context = LocalContext.current
    val stepTracker = remember { StepTracker(context) }
    var steps by remember { mutableStateOf(0) }
    var distance by remember { mutableStateOf(0.0) }
    var permissionGranted by remember { mutableStateOf(false) }
    var sensorAvailable by remember { mutableStateOf(true) }

    // Permission launcher, asked the user for permission to use the sensor step
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted = isGranted
        if (isGranted) {
            stepTracker.startTracking()
        } else {
            Toast.makeText(
                context,
                "Permission denied. Please enable activity recognition in settings.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

   //Checks to see if the sensor is enabled or not, if it is not compatible, it will show the user an error message
    LaunchedEffect(Unit) {
        sensorAvailable = stepTracker.isSensorAvailable()
        if (!sensorAvailable) {
            Toast.makeText(
                context,
                "Step Counter Sensor not available on this device.",
                Toast.LENGTH_LONG
            ).show()
        }

        // Request permission if sensor is available
        if (sensorAvailable && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            permissionGranted = true
            stepTracker.startTracking()
        } else if (sensorAvailable) {
            permissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        }
    }

    // Stop tracking when composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            stepTracker.stopTracking()
        }
    }

    // Periodically update steps and distance if permission is granted
    if (permissionGranted) {
        LaunchedEffect(Unit) {
            while (true) {
                steps = stepTracker.getTotalSteps()
                distance = stepTracker.getDistanceInMiles()
                Log.d("DashboardPage", "Steps updated: $steps, Distance updated: $distance miles") //Testing purposes
                kotlinx.coroutines.delay(1000L) // Update every second
            }
        }
    }

    // UI Design **************************************************************************
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(201, 1, 254),
                        Color(0, 76, 249)
                    )
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title Text *********************************************
        Text(
            text = "Dashboard",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        //Steps Display Text *********************************************
        Text(
            text = "Steps Taken: $steps",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //Distance Display Text *********************************************
        Text(
            text = "Miles Walked: %.2f".format(distance),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardPage() {
    FitEarnTheme {
        DashboardPage(navController = rememberNavController())
    }
}
