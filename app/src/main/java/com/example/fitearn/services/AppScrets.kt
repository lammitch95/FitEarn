package com.example.fitearn.services

import com.example.fitearn.MainActivity
import java.io.File
import java.util.Properties

object AppSecrets {
    private val secretsProperties: Properties = Properties()

    init {
        // Load the secrets from api_secrets/api_secrets.properties
        val filePath = "api_secrets/properties"
        File(filePath).inputStream().use {
            secretsProperties.load(it)
        }
    }

    // Method to get App Center secret
    fun getAppCenterSecret(mainActivity: MainActivity): String {
        return secretsProperties.getProperty("appCenterSecret", "")
    }
}
