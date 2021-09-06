package com.vvsoftdev.testpcsoftcalendar.base

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder(private val view: View)
    : RecyclerView.ViewHolder(view), View.OnClickListener {

    abstract fun bindData(data: Any)

    fun view(): View {
        return view
    }

    fun context(): Context {
        return view.context
    }
}