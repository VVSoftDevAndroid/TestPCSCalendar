package com.vvsoftdev.testpcsoftcalendar.di

import com.vvsoftdev.testpcsoftcalendar.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get(), get()) }
}