package com.example.lab2raymondandobrien.database

import androidx.room.TypeConverter
import com.example.lab2raymondandobrien.models.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    // "|" is used as delimiter because genre names should never contain it.
    @TypeConverter
    fun fromString(value: String): List<String> =
        if (value.isEmpty()) emptyList() else value.split("|")

    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString("|")

    @TypeConverter
    fun fromReviewList(reviews: List<Review>): String = Gson().toJson(reviews)

    @TypeConverter
    fun toReviewList(value: String): List<Review> {
        val type = object : TypeToken<List<Review>>() {}.type
        return Gson().fromJson(value, type) ?: emptyList()
    }
}
