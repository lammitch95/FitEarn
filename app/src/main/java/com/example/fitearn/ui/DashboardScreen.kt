package com.example.fitearn.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.ui.theme.FitEarnTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitearn.R
import com.example.fitearn.utils.LoggedUser

@Composable
fun DashboardScreen(navController: NavHostController) {

    //Member Variables
    val context = LocalContext.current
    val dashboardScreenViewModel: DashboardScreenViewModel = viewModel(
        factory = DashboardScreenViewModel.provideFactory(context)
    )


    // Permission launcher, asked the user for permission to use the sensor step
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        dashboardScreenViewModel.setPermissionGranted(isGranted)
        if (isGranted) {
            dashboardScreenViewModel.startTracking()
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
        dashboardScreenViewModel.checkSensorAvaiable()
        if (!dashboardScreenViewModel.sensorAvailable.value) {
            Toast.makeText(
                context,
                "Step Counter Sensor not available on this device.",
                Toast.LENGTH_LONG
            ).show()
        }

        //Andrea app permission
        // Request permission if sensor is available
        /*
        if (stepTrackerScreenViewModel.sensorAvailable.value && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            stepTrackerScreenViewModel.setPermissionGranted(true)
            stepTrackerScreenViewModel.startTracking()
        } else if (stepTrackerScreenViewModel.sensorAvailable.value) {
            permissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        }
        */

        // Mitchell Phone Testing for Android 9 software
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                dashboardScreenViewModel.setPermissionGranted(true)
                dashboardScreenViewModel.startTracking()
            } else {
                permissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
            }
        } else {
            // For Android 9 and below, permission is granted automatically
            dashboardScreenViewModel.setPermissionGranted(true)
            dashboardScreenViewModel.startTracking()
        }


    }

    DisposableEffect(Unit) {
        onDispose {
            dashboardScreenViewModel.stopTracking()
        }
    }

    // Periodically update steps and distance if permission is granted
    if (dashboardScreenViewModel.permissionGranted.value) {
        LaunchedEffect(Unit) {
            while (true) {

                Log.d("DashboardPage",
                    "Steps updated: ${dashboardScreenViewModel.stepsState.value}, " +
                            "Distance updated: ${dashboardScreenViewModel.distanceState.value} miles") //Testing purposes
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
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Hello ${LoggedUser.loggedInUser?.firstName}",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )
        // Circular Progress Bar with Step Count *********************************************
        Box(
            modifier = Modifier.size(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 20f
                val startAngle = -90f
                val sweepAngle = (dashboardScreenViewModel.stepsState.value % 10000) / 10000f * 360
                drawArc(
                    color = Color.Red,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth)
                )
                drawArc(
                    color = Color.Green,
                    startAngle = startAngle + sweepAngle,
                    sweepAngle = 360 - sweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth)
                )
            }

            // Step Count Text ***********************************************************
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${dashboardScreenViewModel.stepsState.value}",
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(18.dp))

                Icon(
                    painter = painterResource(id = R.drawable.steps_icon),
                    contentDescription = "Feet Icon",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Row with Icons and Stats *******************************************************
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatColumn(icon = R.drawable.coins_icon, value = "${dashboardScreenViewModel.coinsState.value}", label = "Coins")
            StatColumn(icon = R.drawable.dollar_sign_two, value = "${dashboardScreenViewModel.dollarsState.value}", label = "Dollars")
            StatColumn(icon = R.drawable.miles_icon, value = "${dashboardScreenViewModel.distanceState.value}", label = "Miles")
        }

        Spacer(modifier = Modifier.height(42.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Convert Steps Button *********************************************
            Button(
                onClick = {
                    dashboardScreenViewModel.convertStepsToCoins()
                    Toast.makeText(context, "Steps converted to coins!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Convert Steps")
            }
            // Convert Coins Button *********************************************
            Button(
                onClick = {
                    dashboardScreenViewModel.convertCoinsToDollars()
                    Toast.makeText(context, "Coins converted to dollars!", Toast.LENGTH_SHORT)
                        .show()
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Convert Coins")
            }

            // Navigation Bar *********************************************************************************

            Spacer(modifier = Modifier.weight(1f))

            BottomNavigationBar(navController = navController)
        }
    }
}

@Composable
fun StatColumn(icon: Int, value: Any, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(40.dp),
            tint = Color.White
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value.toString(),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Text(
            text = label,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStepTrackerScreen() {
    FitEarnTheme {
        DashboardScreen(navController = rememberNavController())
    }
}
