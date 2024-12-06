package com.example.fitearn.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitearn.R

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    var dateOfBirth: String? = null,
    var weight: String? = null,
    var height: String? = null,
    var phoneNum: String? = null,
    var coinAmount: Int = 0,
    var dollarAmount: Double = 0.0,
    var stepsCount: Int = 0,
    val distance: Double = 0.0,
    var hasUserInfo: Boolean = false,
    var equippedAvatar: String? = "Default",
    var ownAvatars: List<ShopItem> = listOf(
        ShopItem(
            id = 0,
            name = "Default",
            cost = -1,
            imageResId = R.drawable.user_pfp_account,
            itemType = "Avatar"
        )
    )
)