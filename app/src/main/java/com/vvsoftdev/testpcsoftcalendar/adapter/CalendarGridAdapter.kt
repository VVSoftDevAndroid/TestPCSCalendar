package com.vvsoftdev.testpcsoftcalendar.adapter

import android.view.View
import com.vvsoftdev.testpcsoftcalendar.R
import com.vvsoftdev.testpcsoftcalendar.adapter.viewholder.CalendarGridViewHolder
import com.vvsoftdev.testpcsoftcalendar.base.RecyclerViewBaseAdapter
import com.vvsoftdev.testpcsoftcalendar.base.SectionRow
import com.vvsoftdev.testpcsoftcalendar.model.Day


class CalendarGridAdapter(): RecyclerViewBaseAdapter() {
    init {
        addSection(arrayListOf<Number>())
    }

    fun addDayList(days: List<Day>) {
        sections().first().run {
            clear()
            addAll(days)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.grid_cell_item

    override fun viewHolder(layout: Int, view: View) = CalendarGridViewHolder(view)

}