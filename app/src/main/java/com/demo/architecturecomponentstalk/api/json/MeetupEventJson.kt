package com.demo.architecturecomponentstalk.api.json

import com.demo.architecturecomponentstalk.db.entity.Venue

data class MeetupEventJson(
        val id: String,
        val name: String,
        val description: String,
        val venue: Venue
)
