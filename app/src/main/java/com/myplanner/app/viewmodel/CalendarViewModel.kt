package com.myplanner.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
) : ViewModel() {
    private var _date = MutableLiveData(LocalDate.now())
    val date : LiveData<LocalDate> get() = _date

    fun onChangeDate(selectDate: LocalDate) {
        viewModelScope.launch {
            _date.value = selectDate
            _date.postValue(selectDate)
        }
        Log.e("seok","${selectDate}")
    }

    fun getViewModelDate() = _date



}

data class CalendarItemState(
    val isSelected: Boolean,

)