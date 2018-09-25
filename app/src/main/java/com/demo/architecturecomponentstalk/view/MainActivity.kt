package com.demo.architecturecomponentstalk.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.demo.architecturecomponentstalk.R
import com.demo.architecturecomponentstalk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this,
                        R.layout.activity_main)
        val viewModel =
                ViewModelProviders.of(this)
                        .get(MeetupViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }
}
