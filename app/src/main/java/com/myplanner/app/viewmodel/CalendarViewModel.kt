package com.myplanner.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
) : ViewModel() {
    private var _date = MutableLiveData(LocalDate.now())
    var date : LiveData<LocalDate> = _date
    fun onChangeDate(selectDate: LocalDate) {
        _date.value = selectDate
    }

    fun getViewModelDate() = date



}

data class CalendarItemState(
    val isSelected: Boolean,

)