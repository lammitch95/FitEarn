package com.example.fitearn.utils

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class StepTracker(context: Context) : SensorEventListener {

    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var stepCounterSensor: Sensor? = null
    private var sensorInitialValue: Int? = null
    private var initialStepCount: Int? = null
    private var currentStepCount: Int = 0
    private var distanceInMiles: Double = 0.0

    init {
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepCounterSensor == null) {
            Log.e("StepTracker", "Step Counter Sensor not available on this device")
        }
    }

    // Start tracking steps
    fun startTracking() {
        if (stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI)
        } else {
            Log.e("StepTracker", "Cannot start tracking: Step Counter Sensor is not available")
        }
    }

    // Stop tracking steps
    fun stopTracking() {
        if (stepCounterSensor != null) {
            sensorManager.unregisterListener(this, stepCounterSensor)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
            val sensorSteps = event.values[0].toInt()

            if (sensorInitialValue == null) {
                sensorInitialValue = sensorSteps
            }

            val adjustedSensorSteps = sensorSteps - (sensorInitialValue ?: 0)
            currentStepCount = (initialStepCount ?: 0) + adjustedSensorSteps

            calculateDistance()
            Log.d("StepTracker", "Steps: $currentStepCount, Distance: $distanceInMiles miles")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    // Calculate distance in miles based on steps
    @SuppressLint("DefaultLocale")
    private fun calculateDistance() {
        val averageStepLengthInFeet = 2.5 // Average step length in feet
        val stepsPerMile = 5280 / averageStepLengthInFeet
        val distance = currentStepCount / stepsPerMile
        distanceInMiles = String.format("%.2f", distance).toDouble()
    }

    // Get the total steps taken since tracking started
    fun getTotalSteps(): Int {
        return currentStepCount
    }

    fun setCurentStepCount(value: Int) {
        currentStepCount = value

    }

    fun setInitialStepCount(value: Int){
        initialStepCount = value
    }
    // Get the distance in miles walked
    fun getDistanceInMiles(): Double {
        return distanceInMiles
    }

    fun isSensorAvailable(): Boolean {
        return stepCounterSensor != null
    }

}