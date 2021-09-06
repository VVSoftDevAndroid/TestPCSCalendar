package com.vvsoftdev.testpcsoftcalendar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvsoftdev.testpcsoftcalendar.model.Day
import com.vvsoftdev.testpcsoftcalendar.model.MonthYear
import com.vvsoftdev.testpcsoftcalendar.repository.MainRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import java.util.*

@ExperimentalCoroutinesApi
class MainViewModel constructor(
    private val mainRepository: MainRepository,
    private val calendar: Calendar
) : ViewModel() {

    private val _monthYearLiveData = MutableLiveData<MonthYear>()
    val monthYearLiveData: LiveData<MonthYear> = _monthYearLiveData

    private val _currentMonthLiveData = MutableLiveData<Int>()
    val currentMonthLiveData: LiveData<Int> = _currentMonthLiveData

    private val _currentYearLiveData = MutableLiveData<Int>()
    val currentYearLiveData: LiveData<Int> = _currentYearLiveData

    private val _daysListLiveData = MutableLiveData<List<Day>>()
    val daysListLiveData: LiveData<List<Day>> = _daysListLiveData

    private val _isListLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isListLoading: LiveData<Boolean> get() = _isListLoading

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    init {
        initLoad()
    }

    suspend fun loadDate(month: Int, year: Int) {
        try {
            mainRepository.loadDayOfMonth(
                onSuccess = {
                    _isListLoading.postValue(false)
                },
                onError = { _toastLiveData.postValue(it) },
                month = month,
                year = year
            ).collect { value ->
                _monthYearLiveData.postValue(value.first)
                _daysListLiveData.postValue(value.second)
                _currentMonthLiveData.postValue(month)
                _currentYearLiveData.postValue(year)
            }
        } catch (e: Exception) {
            _isListLoading.postValue(false)
            _toastLiveData.postValue(e.message)
        }
    }

    fun selectDay(day: Day) {
        val listDay= mutableListOf<Day>()
        _daysListLiveData.value?.let { listDay.addAll(it) }
        listDay.find { it.day == day.day }?.let {
            listDay.set(listDay.indexOf(it), it.copy(isClicked = !it.isClicked))
        }
        _daysListLiveData.postValue(listDay)
    }

    private fun initLoad() {
        viewModelScope.launch {
            _isListLoading.postValue(true)
            loadDate(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))
        }
    }
}