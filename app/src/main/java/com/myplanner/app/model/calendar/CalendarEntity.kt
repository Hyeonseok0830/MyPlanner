package com.myplanner.app.model.calendar

import java.time.LocalDate
import java.time.LocalTime

data class CalendarEntity(
    val id: Int,
    val title: String = "제목",
    val location: String? = "서울",
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime? = LocalTime.now(),
    val isNotify: Boolean = true,
    val isDone: Boolean = true
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
