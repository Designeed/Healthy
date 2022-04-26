package com.example.healthy.data.utils

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(param: Long?): Date? {
        return if (param == null) null else Date(param)
    }

    @TypeConverter
    fun dateTimestamp(date: Date?): Long? {
        return date?.time
    }
}