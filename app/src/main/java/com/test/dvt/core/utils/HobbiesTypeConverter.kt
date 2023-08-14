package com.test.dvt.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.test.dvt.data.models.current_forecast.Daily

class HobbiesTypeConverter {

    @TypeConverter
    fun dailyToJson(value: List<Daily>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToDaily(value: String) = Gson().fromJson(value, Array<Daily>::class.java).toList()
}
