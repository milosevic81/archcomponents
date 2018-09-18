package com.demo.architecturecomponentstalk.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.demo.architecturecomponentstalk.api.json.MeetupEventJson

@Entity(tableName = "event")
data class MeetupEvent(
        @PrimaryKey
        val id: String,
        val name: String,
        val description: String,
        @Embedded(prefix = "venue_")
        val venue: Venue
) {
    constructor(json: MeetupEventJson) : this(json.id, json.name, json.description, json.venue)
}
