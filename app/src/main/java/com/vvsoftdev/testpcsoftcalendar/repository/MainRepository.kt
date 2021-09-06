package com.vvsoftdev.testpcsoftcalendar.repository

import com.vvsoftdev.testpcsoftcalendar.model.Day
import com.vvsoftdev.testpcsoftcalendar.model.MonthYear
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainRepository(
    private val calendar: Calendar
)  {

    @ExperimentalCoroutinesApi
    suspend fun loadDayOfMonth(
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
        month: Int,
        year: Int
    ): Flow<Pair<MonthYear, List<Day>>> = flow {
        try {
            val listDay= mutableListOf<Day>()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            (1..calendar.getActualMaximum(Calendar.DATE)).toList().forEach { listDay.add(Day(day = it.toString(), isClicked = false)) }
            val monthStr = SimpleDateFormat("MMMM", Locale.getDefault())
            val res = MonthYear(monthStr.format(calendar.time), year.toString(), calendar.getActualMaximum(Calendar.DATE)) to listDay
            emit(res)
            onSuccess()
        } catch (ex: Exception) {
            ex.message?.let { onError(it) }
        }
    }.flowOn(Dispatchers.IO)
}