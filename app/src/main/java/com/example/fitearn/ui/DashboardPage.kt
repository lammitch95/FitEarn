package com.example.fitearn.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitearn.utils.LoggedUser


@Composable
fun DashboardPage(navController: NavHostController) {

    val context = LocalContext.current
    val dashboardPageViewModel: DashboardPageViewModel = viewModel(
        factory = DashboardPageViewModel.provideFactory(context)
    )

    /*

    // Permission launcher, asked the user for permission to use the sensor step
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        dashboardPageViewModel.setPermissionGranted(isGranted)
        if (isGranted) {
            dashboardPageViewModel.startTracking()
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
        dashboardPageViewModel.checkSensorAvaiable()
        if (!dashboardPageViewModel.sensorAvailable.value) {
            Toast.makeText(
                context,
                "Step Counter Sensor not available on this device.",
                Toast.LENGTH_LONG
            ).show()
        }

        // Request permission if sensor is available
        /*if (dashboardPageViewModel.sensorAvailable.value && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            dashboardPageViewModel.setPermissionGranted(true)
            dashboardPageViewModel.startTracking()
        } else if (dashboardPageViewModel.sensorAvailable.value) {
            permissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        }*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                dashboardPageViewModel.setPermissionGranted(true)
                dashboardPageViewModel.startTracking()
            } else {
                permissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
            }
        } else {
            // For Android 9 and below, permission is granted automatically
            dashboardPageViewModel.setPermissionGranted(true)
            dashboardPageViewModel.startTracking()
        }

    }



    // Stop tracking when composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            dashboardPageViewModel.stopTracking()
        }
    }

    // Periodically update steps and distance if permission is granted
    if (dashboardPageViewModel.permissionGranted.value) {
        LaunchedEffect(Unit) {
            while (true) {
                //steps = stepTracker.getTotalSteps()
                //distance = stepTracker.getDistanceInMiles()
                Log.d("DashboardPage", "Steps updated: ${dashboardPageViewModel.stepsState.value}, Distance updated: ${dashboardPageViewModel.distanceState.value} miles") //Testing purposes
                kotlinx.coroutines.delay(1000L) // Update every second
            }
        }
    }
    */


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
            text = "Hello ${LoggedUser.loggedInUser?.firstName}",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )
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
            text = "Steps Taken: ${dashboardPageViewModel.stepsState.value}",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Convert Steps Button *********************************************
        Button(
            onClick = {
                //coins += steps / 10 // Convert steps to coins
                //steps = 0 // Reset steps
                dashboardPageViewModel.convertStepsToCoins()
                Toast.makeText(context, "Steps converted to coins!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Convert Steps")
        }

        // Coins Display *********************************************
        Text(
            text = "Coins Earned: ${dashboardPageViewModel.coinsState.value}",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Convert Coins Button *********************************************
        Button(
            onClick = {
                //dollars += coins / 100.0 // Convert coins to dollars
                //coins = 0 // Reset coins
                dashboardPageViewModel.convertCoinsToDollars()
                Toast.makeText(context, "Coins converted to dollars!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Convert Coins")
        }

        // Dollars Display *********************************************
        Text(
            text = "Dollars Earned: $%.2f".format(dashboardPageViewModel.dollarsState.value),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //Distance Display Text *********************************************
        Text(
            text = "Miles Walked: %.2f".format(dashboardPageViewModel.distanceState.value),
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