package com.vvsoftdev.testpcsoftcalendar.adapter.viewholder

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vvsoftdev.testpcsoftcalendar.MainActivity
import com.vvsoftdev.testpcsoftcalendar.base.BaseRecyclerViewHolder
import com.vvsoftdev.testpcsoftcalendar.binding.bindings
import com.vvsoftdev.testpcsoftcalendar.databinding.GridCellItemBinding
import com.vvsoftdev.testpcsoftcalendar.model.Day
import com.vvsoftdev.testpcsoftcalendar.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


class CalendarGridViewHolder(view: View) : BaseRecyclerViewHolder(view) {

    private lateinit var data: Day
    private val binding: GridCellItemBinding by  bindings(view)

    init {
        view.setOnClickListener(this)
    }

    @ExperimentalCoroutinesApi
    override fun bindData(data: Any) {
        if (data is Day ) {
            this.data = data
            drawItemUI()
        }
    }

    @ExperimentalCoroutinesApi
    override fun onClick(view: View?) {
        view?.let {
            val vm = ViewModelProvider(it.context as MainActivity).get(MainViewModel::class.java)
            vm.selectDay(data)
        }
    }

    private fun drawItemUI() {
        binding.apply {
            day = data
            executePendingBindings()
        }
    }

}