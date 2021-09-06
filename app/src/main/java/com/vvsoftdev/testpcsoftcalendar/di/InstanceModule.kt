package com.vvsoftdev.testpcsoftcalendar.di

import org.koin.dsl.module
import java.util.*

val instanceModule = module {
    single { Calendar.getInstance() }
}