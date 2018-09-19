package com.demo.architecturecomponentstalk.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.RoomDatabase
import com.demo.architecturecomponentstalk.DemoApp
import com.demo.architecturecomponentstalk.api.MeetupApi
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
    override fun init(configuration: DatabaseConfiguration) {
        super.init(configuration)
        reloadEvents()
    }

    /**
     * Clear database and reload all meetup events from API
     */
    private fun reloadEvents() {
        DemoApp.appExecutors.diskIO().execute {
            clearAllTables()
            val events = MeetupApi.getEvents("InterVenture-Meetups")
            if (events != null) {
                for (ev in events) {
                    meetupEventDao().insert(MeetupEvent(ev))
                }
            }
        }
    }
}