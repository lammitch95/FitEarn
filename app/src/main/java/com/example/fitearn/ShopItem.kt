// ShopItem.kt
package com.example.fitearn

data class ShopItem(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String = "", // Placeholder for an image URL or resource ID
    val isPurchased: Boolean = false
)
