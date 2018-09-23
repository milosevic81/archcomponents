package com.demo.architecturecomponentstalk.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
