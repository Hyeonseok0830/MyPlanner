package com.myplanner.app.repository.calendar

import com.myplanner.app.model.calendar.Calendar
import com.myplanner.app.model.calendar.CalendarDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CalendarRepository (
    private val eventDao: CalendarDao
){
    suspend fun getEventDate() : Calendar {
        return withContext(Dispatchers.IO){
            eventDao.getCalendarEvent()[0]
        }
    }

    suspend fun addEvent(event : Calendar) {
        withContext(Dispatchers.IO) {
            eventDao.insert(event)
        }
    }
}