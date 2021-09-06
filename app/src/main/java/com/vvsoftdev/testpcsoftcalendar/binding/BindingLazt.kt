package com.vvsoftdev.testpcsoftcalendar.binding

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

inline fun <reified T : ViewDataBinding> bindings(view: View): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) {
        requireNotNull(DataBindingUtil.bind<T>(view)) { "cannot find the matched view to layout" }
    }