package com.demo.architecturecomponentstalk

import android.app.Application
import android.arch.persistence.room.Room
import com.demo.architecturecomponentstalk.db.MeetupDb

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 */
class DemoApp : Application() {

    lateinit var DB: MeetupDb

    companion object {
        val appExecutors = AppExecutors()
    }

    override fun onCreate() {
        super.onCreate()

        DB = Room.databaseBuilder(applicationContext,
                MeetupDb::class.java, "demo.db")
                .build()
    }
}