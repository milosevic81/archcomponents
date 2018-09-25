package com.demo.architecturecomponentstalk.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.demo.architecturecomponentstalk.db.entity.MeetupEvent

@Dao
interface MeetupEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meetupEvent: MeetupEvent)

    @Delete
    fun delete(meetup: MeetupEvent)

    @Delete
    fun delete(meetups: List<MeetupEvent>)

    @Update
    fun update(meetup: MeetupEvent)

    @Query("SELECT * FROM event WHERE id = :id")
    fun findById(id: String): LiveData<MeetupEvent>

    @Query("SELECT * FROM event WHERE id = :id")
    fun findById2(id: String): MeetupEvent

    @Query("SELECT * FROM event")
    fun findAll(): LiveData<List<MeetupEvent>>
}
