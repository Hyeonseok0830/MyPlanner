package com.myplanner.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myplanner.app.model.calendar.Calendar
import com.myplanner.app.repository.calendar.CalendarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarRepository: CalendarRepository
) : ViewModel() {
    private var _date = MutableLiveData(LocalDate.now())
    val date : LiveData<LocalDate> get() = _date
    init {
        viewModelScope.launch {
            //_date.value = calendarRepository.getEventDate().date
        }
    }
    fun onChangeDate(selectDate: LocalDate) {
        viewModelScope.launch {
            _date.value = selectDate
            _date.postValue(selectDate)
        }
    }

    suspend fun addEvent(eventItem : Calendar) {
        viewModelScope.launch {
            eventItem.let { calendarRepository.addEvent(it) }
        }

    }
    fun getViewModelDate() = _date



}