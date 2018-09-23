package com.demo.architecturecomponentstalk.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
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
