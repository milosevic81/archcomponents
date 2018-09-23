package com.demo.architecturecomponentstalk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.architecturecomponentstalk.adapter.EventsAdapter
import com.demo.architecturecomponentstalk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MeetupViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        val adapter = EventsAdapter()
        binding.eventsList.adapter = adapter

        viewModel.events.observe(this, Observer { events ->
            if (events != null) adapter.submitList(events)
        })
    }
}
