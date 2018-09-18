package com.demo.architecturecomponentstalk.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.demo.architecturecomponentstalk.db.entity.MeetupEvent

@Database(
        entities = [
            MeetupEvent::class
        ],
        version = 1,
        exportSchema = false
)
abstract class MeetupDb : RoomDatabase() {
    abstract fun meetupEventDao(): MeetupEventDao
}