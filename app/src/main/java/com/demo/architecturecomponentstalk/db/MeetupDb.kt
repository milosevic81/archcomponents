package com.demo.architecturecomponentstalk.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.RoomDatabase
import com.demo.architecturecomponentstalk.DemoApp
import com.demo.architecturecomponentstalk.db.entity.MeetupEvent
import com.demo.architecturecomponentstalk.db.entity.Venue

@Database(
        entities = [
            MeetupEvent::class
        ],
        version = 1,
        exportSchema = false
)
abstract class MeetupDb : RoomDatabase() {
    abstract fun meetupEventDao(): MeetupEventDao
    override fun init(configuration: DatabaseConfiguration) {
        super.init(configuration)
        DemoApp.appExecutors.diskIO().execute {
            meetupEventDao().insert(MeetupEvent("123", "Stub meetup", "Meetup desc", Venue("12", "venue name")))
        }
    }
}