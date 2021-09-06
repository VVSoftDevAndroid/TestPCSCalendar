package com.vvsoftdev.testpcsoftcalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vvsoftdev.testpcsoftcalendar.adapter.CalendarGridAdapter
import com.vvsoftdev.testpcsoftcalendar.base.DatabindingFragment
import com.vvsoftdev.testpcsoftcalendar.databinding.MainFragmentBinding
import com.vvsoftdev.testpcsoftcalendar.model.Day
import com.vvsoftdev.testpcsoftcalendar.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.getSharedViewModel

class MainFragment : DatabindingFragment() {
    private lateinit var binding: MainFragmentBinding

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding<MainFragmentBinding>(inflater, R.layout.main_fragment, container).apply {
        viewmodel = getSharedViewModel() as MainViewModel
        lifecycleOwner = viewLifecycleOwner
        adapter = CalendarGridAdapter()
        this@MainFragment.binding = this
    }.root

}