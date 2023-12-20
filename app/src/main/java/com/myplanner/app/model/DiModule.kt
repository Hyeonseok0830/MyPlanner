package com.myplanner.app.model

import android.content.Context
import androidx.room.Room
import com.myplanner.app.model.calendar.CalendarDao
import com.myplanner.app.model.calendar.CalendarDatabase
import com.myplanner.app.repository.calendar.CalendarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiModule {
    @Singleton
    @Provides
    fun provideCalendarDatabase(@ApplicationContext context: Context) : CalendarDatabase {
        return Room
            .databaseBuilder(
                context,
                CalendarDatabase::class.java,
                CalendarDatabase.DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideCalendarDAO(calendarDB: CalendarDatabase): CalendarDao {
        return calendarDB.calendarDao()
    }

    @Singleton
    @Provides
    fun provideCalendarRepository(
        calendarDao: CalendarDao
    ) : CalendarRepository{
        return CalendarRepository(calendarDao)
    }
}