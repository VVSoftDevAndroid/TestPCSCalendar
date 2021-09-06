package com.vvsoftdev.testpcsoftcalendar

import com.vvsoftdev.testpcsoftcalendar.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

const val LEFT = "LEFT"
const val RIGHT = "RIGHT"
const val UP = "UP"
const val DOWN = "DOWN"

@ExperimentalCoroutinesApi
fun swipe(direction: String, viewModel: MainViewModel) {
    var month = viewModel.currentMonthLiveData.value
    var year = viewModel.currentYearLiveData.value
    if(month != null && year != null) {
        when (direction) {
            LEFT -> month++
            RIGHT -> month--
            UP -> year++
            DOWN -> year--
        }
        runBlocking {
            withContext(Dispatchers.IO) {
                viewModel.loadDate(month = month, year = year)
            }
        }
    }

}