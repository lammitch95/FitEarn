package com.example.fitearn.ui

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.utils.StepTracker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardPageViewModel(private val stepTracker: StepTracker) : ViewModel() {

    companion object {
        // Provide Factory for the ViewModel
        fun provideFactory(context: Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(DashboardPageViewModel::class.java)) {
                        // Initialize StepTracker with the provided context
                        val stepTracker = StepTracker(context)
                        return DashboardPageViewModel(stepTracker) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    var permissionGranted = mutableStateOf(false)
        private set
    var sensorAvailable = mutableStateOf(true)
        private set
    var stepsState = mutableStateOf(0)
        private set
    var stepsCanConvert = mutableStateOf(0)
        private set
    var distanceState = mutableStateOf(0.0)
        private set
    var coinsState = mutableStateOf(0)
        private set
    var dollarsState = mutableStateOf(0.0)
        private set

    fun startTracking() {
        stepTracker.startTracking()
        viewModelScope.launch {
            while (true) {
                stepsState.value = stepTracker.getTotalSteps()
                distanceState.value = stepTracker.getDistanceInMiles()
                delay(1000L) // Update every second
            }
        }
    }

    fun checkSensorAvaiable(){
        sensorAvailable.value = stepTracker.isSensorAvailable()
    }


    fun setPermissionGranted(value: Boolean){
        permissionGranted.value = value
    }

    fun setSensorAvailable(value: Boolean){
        sensorAvailable.value = value
    }

    fun stopTracking() {
        stepTracker.stopTracking()
    }

    fun convertStepsToCoins() {
        coinsState.value += stepsState.value / 10
        stepsState.value = 0 // Reset steps
    }

    fun convertCoinsToDollars() {
        dollarsState.value += coinsState.value / 100.0
        coinsState.value = 0 // Reset coins
    }
}


