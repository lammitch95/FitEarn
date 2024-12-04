package com.example.fitearn.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.utils.LoggedUser
import com.example.fitearn.utils.StepTracker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StepTrackerScreenViewModel(private val stepTracker: StepTracker, private val appDatabase: AppDatabase) : ViewModel() {

        companion object {
            // Provide Factory for the ViewModel
            fun provideFactory(context: Context): ViewModelProvider.Factory {
                return object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        if (modelClass.isAssignableFrom(StepTrackerScreenViewModel::class.java)) {
                            // Initialize StepTracker with the provided context
                            val stepTracker = StepTracker(context)
                            val appDatabase = AppDatabase.getDatabase(context)
                            return StepTrackerScreenViewModel(stepTracker, appDatabase) as T
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

        init {


            if(LoggedUser.loggedInUser != null){
                val initalStepCount = LoggedUser.loggedInUser!!.stepsCount ?: 0
                stepsState.value = initalStepCount
                stepTracker.setInitialStepCount(initalStepCount)
                coinsState.value = LoggedUser.loggedInUser!!.coinAmount
                dollarsState.value = LoggedUser.loggedInUser!!.dollarAmount

            }
        }
        fun startTracking() {
            stepTracker.startTracking()
            viewModelScope.launch {
                while (true) {
                    val totalSteps = stepTracker.getTotalSteps()
                    stepsState.value = totalSteps
                    distanceState.value = stepTracker.getDistanceInMiles()

                    if(LoggedUser.loggedInUser != null){
                        LoggedUser.loggedInUser!!.stepsCount = stepsState.value
                        stepTracker.setCurentStepCount(stepsState.value)
                    }

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

            stepTracker.setCurentStepCount(0)
            stepTracker.setInitialStepCount(0)
            stepTracker.setSensortInitial(null)

            if(LoggedUser.loggedInUser != null){
                LoggedUser.loggedInUser!!.coinAmount = coinsState.value
                LoggedUser.loggedInUser!!.stepsCount = stepsState.value
            }
            saveToDatabase()
        }

        @SuppressLint("DefaultLocale")
        fun convertCoinsToDollars() {
            dollarsState.value += coinsState.value / 100.0
            if(LoggedUser.loggedInUser != null){
                LoggedUser.loggedInUser!!.dollarAmount = dollarsState.value
            }
            dollarsState.value = String.format("%.2f", dollarsState.value).toDouble()
            coinsState.value = 0 // Reset coins
            saveToDatabase()
        }

        fun saveToDatabase(){
            viewModelScope.launch {
                val userDao = appDatabase.userDao()
                LoggedUser.loggedInUser?.let { userDao.updateUser(it) }
            }
        }
}
