package com.myplanner.app.model.calendar

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDate
import java.time.LocalTime

@Database(entities = [Calendar::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class CalendarDatabase : RoomDatabase() {
    abstract fun calendarDao(): CalendarDao

    companion object {
        const val DATABASE_NAME = "MyPlanner.db"
    }
}
class DateTimeConverter {

    @TypeConverter
    fun localDateToString(localDate: LocalDate?): String? =
        localDate?.toString()

    @TypeConverter
    fun stringToLocalDate(string: String?): LocalDate? =
        LocalDate.parse(string)

    @TypeConverter
    fun localTimeToString(localTime: LocalTime?): String? =
        localTime?.toString()

    @TypeConverter
    fun stringToLocalTime(string: String?): LocalTime? =
        string?.let { LocalTime.parse(string) }
}