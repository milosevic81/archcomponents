package com.demo.architecturecomponentstalk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.demo.architecturecomponentstalk.db.MeetupDb

class MainActivity : AppCompatActivity() {

    private lateinit var db: MeetupDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
