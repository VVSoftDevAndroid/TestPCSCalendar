package com.vvsoftdev.testpcsoftcalendar.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

abstract class DataBindingBaseActivity : AppCompatActivity() {

    protected fun <T> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView(this, resId) }

}