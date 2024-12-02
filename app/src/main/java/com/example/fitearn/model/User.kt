package com.example.fitearn.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val dateOfBirth: String? = null,
    val weight: Double? = null,
    val height: Double? = null,
    val phoneNum: String? = null,
    val coinAmount: Int = 0,
    val stepsCount: Int = 0,
    val distance: Double = 0.0
)