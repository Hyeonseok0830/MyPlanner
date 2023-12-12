package com.myplanner.app.model

import android.content.Context
import androidx.room.Room
import com.myplanner.app.model.calendar.CalendarDao
import com.myplanner.app.model.calendar.CalendarDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDodoDatabase(
        @ApplicationContext context: Context
    ): CalendarDatabase = Room
        .databaseBuilder(context, CalendarDatabase::class.java, "mp_database")
        .build()

    @Singleton
    @Provides
    fun provideTodoDao(database: CalendarDatabase): CalendarDao = database.calendarDao()
}