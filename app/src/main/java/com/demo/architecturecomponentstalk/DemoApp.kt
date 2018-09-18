package com.demo.architecturecomponentstalk

import android.app.Application
import android.arch.persistence.room.Room
import com.demo.architecturecomponentstalk.db.MeetupDb

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 */
class DemoApp : Application() {

    companion object {
        lateinit var DB: MeetupDb
        val appExecutors = AppExecutors()
    }

    override fun onCreate() {
        super.onCreate()

        DB = Room.databaseBuilder(applicationContext, MeetupDb::class.java, "demodb").build()
    }
}