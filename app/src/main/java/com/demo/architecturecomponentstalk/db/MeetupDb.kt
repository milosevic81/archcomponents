package com.demo.architecturecomponentstalk.db

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.RoomDatabase
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
            val events = MeetupApi.getEventsByUrlName("InterVenture-Meetups")
            if (events != null) {
                clearAllTables()
                for (ev in events) {
                    meetupEventDao().insert(MeetupEvent(ev))
                }
            }
        }
    }
}