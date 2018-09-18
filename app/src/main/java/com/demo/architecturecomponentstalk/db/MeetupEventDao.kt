package com.demo.architecturecomponentstalk.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.demo.architecturecomponentstalk.db.entity.MeetupEvent

@Dao
interface MeetupEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meetupEvent: MeetupEvent)

    @Query("SELECT * FROM event WHERE id = :id")
    fun findById(id: String): LiveData<MeetupEvent>

    @Query("SELECT * FROM event")
    fun findAll(): LiveData<List<MeetupEvent>>
}
