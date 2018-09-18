package com.demo.architecturecomponentstalk.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.RoomDatabase
import com.demo.architecturecomponentstalk.DemoApp
import com.demo.architecturecomponentstalk.api.MeetupApi
import com.demo.architecturecomponentstalk.db.entity.MeetupEvent
import com.demo.architecturecomponentstalk.db.entity.Venue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            //meetupEventDao().insert(MeetupEvent("123", "Stub meetup", "Meetup desc", Venue("12", "venue name")))
            clearAllTables()
        }
        DemoApp.appExecutors.diskIO().execute {
            val apiKey = "5919532b582856507c78552031717d2d"
            val api = Retrofit.Builder()
                    .baseUrl("https://api.meetup.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MeetupApi::class.java)
            val call = api.getEventById("InterVenture-Meetups", "254610194", apiKey, true)
            val body = call.execute().body()
            body?.let {
                val ev = MeetupEvent(it)
                meetupEventDao().insert(ev)
                meetupEventDao().insert(MeetupEvent("123", "Stub meetup", "Meetup desc", Venue("12", "venue name")))
            }
        }
    }
}