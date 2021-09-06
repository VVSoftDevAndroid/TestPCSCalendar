package com.vvsoftdev.testpcsoftcalendar

import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.vvsoftdev.testpcsoftcalendar.base.DataBindingBaseActivity
import com.vvsoftdev.testpcsoftcalendar.databinding.ActivityMainBinding

class MainActivity : DataBindingBaseActivity() {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {

            lifecycleOwner = this@MainActivity
            executePendingBindings()
        }
        fetchFragment()
    }

    private fun fetchFragment() {
        supportFragmentManager.commit {
            replace<MainFragment>(R.id.fragment_container_view)
        }
    }
}