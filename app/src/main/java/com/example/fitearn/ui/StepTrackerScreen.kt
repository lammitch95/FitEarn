package com.example.fitearn.ui

import android.Manifest
import android.content.pm.PackageManager
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitearn.ui.theme.FitEarnTheme
import com.example.fitearn.utils.StepTracker
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitearn.R

@Composable
fun StepTracker(navController: NavHostController) {

    //Member Variables
    val context = LocalContext.current
    val stepTracker = remember { StepTracker(context) }
    val StepTrackerScreenViewModel: StepTrackerScreenViewModel = viewModel(
        factory = StepTrackerScreenViewModel.provideFactory(stepTracker)
    )
    var steps by remember { mutableStateOf(0) }
    var distance by remember { mutableStateOf(0.0) }
    var coins by remember { mutableStateOf(0) }
    var dollars by remember { mutableStateOf(0.0) }
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
                Log.d(
                    "DashboardPage",
                    "Steps updated: $steps, Distance updated: $distance miles"
                ) //Testing purposes
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
        // Circular Progress Bar with Step Count *********************************************
        Box(
            modifier = Modifier.size(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 20f
                val startAngle = -90f
                val sweepAngle = (steps % 10000) / 10000f * 360
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
                    text = "$steps",
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
            StatColumn(icon = R.drawable.coins_icon, value = "$coins", label = "Coins")
            StatColumn(icon = R.drawable.dollar_sign_two, value = "$dollars", label = "Dollars")
            StatColumn(icon = R.drawable.miles_icon, value = "$distance", label = "Miles")
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
                    coins += steps / 10 // Convert steps to coins
                    steps = 0 // Reset steps
                    Toast.makeText(context, "Steps converted to coins!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Convert Steps")
            }
            // Convert Coins Button *********************************************
            Button(
                onClick = {
                    dollars += coins / 100.0 // Convert coins to dollars
                    coins = 0 // Reset coins
                    Toast.makeText(context, "Coins converted to dollars!", Toast.LENGTH_SHORT)
                        .show()
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Convert Coins")
            }

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
fun PreviewDashboardPage() {
    FitEarnTheme {
        StepTracker(navController = rememberNavController())
    }
}
