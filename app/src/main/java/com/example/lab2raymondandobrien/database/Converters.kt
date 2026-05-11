package com.example.lab2raymondandobrien.database

import androidx.room.TypeConverter

class Converters {
    // "|" is used as delimiter because genre names should never contain it.
    @TypeConverter
    fun fromString(value: String): List<String> =
        if (value.isEmpty()) emptyList() else value.split("|")

    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString("|")
}
