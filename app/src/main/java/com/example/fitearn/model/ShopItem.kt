package com.example.fitearn.model

import com.example.fitearn.R

data class ShopItem(
    val id: Int,
    val name: String,
    val cost: Int,
    val imageResId: Int,
    val itemType: String
)

object ShopItemsRepository {

    val redeemItems = listOf(
        ShopItem(
            id = 1,
            name = "Bear",
            cost = 25,
            imageResId = R.drawable.amazon,
            itemType = "Redeem"
        ),
        ShopItem(
            id = 2,
            name = "Astronaut",
            cost = 50,
            imageResId = R.drawable.amazon,
            itemType = "Redeem"
        ),
        ShopItem(
            id = 3,
            name = "Duck",
            cost = 100,
            imageResId = R.drawable.amazon,
            itemType = "Redeem"
        )
    )

    val avatarItems = listOf(
        ShopItem(
            id = 1,
            name = "Bear",
            cost = 5000,
            imageResId = R.drawable.bear,
            itemType = "Avatar"
        ),
        ShopItem(
            id = 2,
            name = "Astronaut",
            cost = 10000,
            imageResId = R.drawable.astronaut,
            itemType = "Avatar"
        ),
        ShopItem(
            id = 3,
            name = "Duck",
            cost = 1000,
            imageResId = R.drawable.chicken,
            itemType = "Avatar"
        ),
        ShopItem(
            id = 4,
            name = "Dog",
            cost = 2500,
            imageResId = R.drawable.dog,
            itemType = "Avatar"
        ),
        ShopItem(
            id = 5,
            name = "Rabbit",
            cost = 250,
            imageResId = R.drawable.rabbit,
            itemType = "Avatar"
        ),
    )
}