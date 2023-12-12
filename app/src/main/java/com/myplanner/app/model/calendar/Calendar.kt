package com.myplanner.app.model.calendar

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "calendar")
data class Calendar (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val location: String?,
    val date: LocalDate,
    val time: LocalTime?,
    val isNotify: Boolean,
    val isDone: Boolean
)
fun Calendar.toEntity() = CalendarEntity(
    id = this.id,
    title = this.title,
    location = this.location,
    date = this.date,
    time = this.time,
    isNotify = this.isNotify,
    isDone = this.isDone
)