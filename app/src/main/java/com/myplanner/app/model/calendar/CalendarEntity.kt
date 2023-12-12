package com.myplanner.app.model.calendar

import java.time.LocalDate
import java.time.LocalTime

data class CalendarEntity(
    val id: Int,
    val title: String,
    val location: String?,
    val date: LocalDate,
    val time: LocalTime?,
    val isNotify: Boolean,
    val isDone: Boolean
)

fun CalendarEntity.toDataEntity() = Calendar(
    id = this.id,
    title = this.title,
    location = this.location,
    date = this.date,
    time = this.time,
    isNotify = this.isNotify,
    isDone = this.isDone
)
