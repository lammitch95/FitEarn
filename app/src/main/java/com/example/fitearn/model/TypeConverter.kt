package com.example.fitearn.model

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson


class Converters {
    private val gson = Gson()

    @TypeConverter
    fun convertShopItemsToJson(value: List<ShopItem>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun convertJsonToShopItems(value: String): List<ShopItem> {
        val listType = object : TypeToken<List<ShopItem>>() {}.type
        return gson.fromJson(value, listType)
    }
}
