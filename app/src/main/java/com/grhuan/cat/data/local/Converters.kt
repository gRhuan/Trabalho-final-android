package com.grhuan.cat.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.grhuan.cat.data.model.Breed

class Converters {
    @TypeConverter
    fun fromBreedList(breeds: List<Breed>): String {
        return Gson().toJson(breeds)
    }

    @TypeConverter
    fun toBreedList(data: String): List<Breed> {
        val listType = object : TypeToken<List<Breed>>() {}.type
        return Gson().fromJson(data, listType)
    }
}