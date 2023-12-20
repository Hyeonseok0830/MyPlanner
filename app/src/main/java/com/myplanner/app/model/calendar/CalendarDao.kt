package com.myplanner.app.model.calendar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CalendarDao {
    @Query("SELECT * FROM calendar")
    suspend fun getCalendarEvent(): List<Calendar>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Calendar)
}