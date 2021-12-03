package com.magnalleexample.geekbrains_movieviewer.data.helperDataClasses.Room

import androidx.room.TypeConverter
import java.util.Date
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.magnalleexample.geekbrains_movieviewer.domain.entity.Genre

class GenresConverter {

    @TypeConverter
    fun fromStringGenres(value: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListGenres(list: List<Genre>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}