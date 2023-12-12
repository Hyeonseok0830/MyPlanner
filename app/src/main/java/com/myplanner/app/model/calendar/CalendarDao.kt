package com.myplanner.app.model.calendar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.time.LocalDate

@Dao
interface CalendarDao {
    @Query("SELECT * FROM calendar")
    suspend fun fetchTodoList(): List<Calendar>

    @Query("SELECT * FROM calendar WHERE date = :targetDate")
    suspend fun fetchTodoListWithDate(targetDate: LocalDate): List<Calendar>

    @Query("SELECT * FROM calendar WHERE id = :id")
    suspend fun fetchTodo(id: Int) : Calendar

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(todo: Calendar)

    @Update
    suspend fun editTodo(todo: Calendar)

    @Delete
    suspend fun deleteTodo(todo: Calendar)

    @Query("UPDATE calendar SET isDone = :isDone WHERE id = :id")
    suspend fun doneTodo(id: Int, isDone: Boolean)

    @Query("UPDATE calendar SET isNotify = :isNotify WHERE id = :id")
    suspend fun notifyChange(id: Int, isNotify: Boolean)

    @Query("SELECT * FROM calendar WHERE id = :id")
    suspend fun fetchTodoDetail(id: Int): Calendar
}