package com.vvsoftdev.testpcsoftcalendar.binding

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vvsoftdev.testpcsoftcalendar.*
import com.vvsoftdev.testpcsoftcalendar.adapter.CalendarGridAdapter
import com.vvsoftdev.testpcsoftcalendar.base.RecyclerViewBaseAdapter
import com.vvsoftdev.testpcsoftcalendar.model.Day
import com.vvsoftdev.testpcsoftcalendar.viewmodel.MainViewModel
import kotlinx.coroutines.*

object ViewBinding {

    @JvmStatic
    @BindingAdapter("dayList")
    fun bindDayList(view: RecyclerView, days: List<Day>?) {
        if (!days.isNullOrEmpty()) {
            val adapter: CalendarGridAdapter = view.adapter as CalendarGridAdapter
            adapter.addDayList(days)
        }
    }

    @JvmStatic
    @BindingAdapter("setLayoutManager")
    fun setlayoutManager(view: RecyclerView, columns: Int) {
        view.layoutManager = GridLayoutManager(view.context, columns)
    }

    @ExperimentalCoroutinesApi
    @SuppressLint("ClickableViewAccessibility")
    @JvmStatic
    @BindingAdapter("setGestureListener")
    fun setGestureListener(view: View, viewModel: MainViewModel) =
        view.setOnTouchListener(object: OnSwipeTouchListener(view.context) {
            override fun onSwipeLeft() {
                swipe(LEFT, viewModel)
            }
            override fun onSwipeRight() {
                swipe(RIGHT, viewModel)
            }

            override fun onSwipeTop() {
                swipe(UP, viewModel)
            }

            override fun onSwipeBottom() {
                swipe(DOWN, viewModel)
            }
        })

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: RecyclerView, text: String?) {
        text?.let {
            if (it.isNotEmpty())
                Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("backgroundDay")
    fun bindToast(view: ConstraintLayout, selection: Boolean) {
        if(selection) {
            view.context.getDrawable(R.drawable.layout_border_clicked).also { view.background = it }
        } else
            view.context.getDrawable(R.drawable.layout_border).also { view.background = it }
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean?) {
        if (shouldBeGone == true) {
            view.gone(true)
        } else {
            view.gone(false)
        }
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, baseAdapter: RecyclerViewBaseAdapter) {
        view.adapter = baseAdapter
    }

}