package com.vvsoftdev.testpcsoftcalendar.di

import com.vvsoftdev.testpcsoftcalendar.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRepository(get()) }
}