package com.demo.architecturecomponentstalk.db

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.demo.architecturecomponentstalk.api.json.MeetupEventJson
import com.demo.architecturecomponentstalk.db.entity.MeetupEvent
import com.demo.util.LiveDataTestUtil.getValue
import com.google.gson.Gson
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InsertAndLoadTest : DbTest() {

    @Test
    fun insertAndLoadCollection() {
        val json = loadFromJsonResource<MeetupEventJson>("meetupEvent.json")
        val event = MeetupEvent(json)
        db.meetupEventDao().insert(event)
        val loaded = getValue(db.meetupEventDao().findById(event.id))

        Assert.assertThat(loaded.description, CoreMatchers.`is`(event.description))
        Assert.assertThat(loaded.name, CoreMatchers.`is`(event.name))
        Assert.assertThat(loaded.venue.name, CoreMatchers.`is`(event.venue.name))
    }

    private inline fun <reified T> loadFromJsonResource(fileName: String): T {
        val json = loadAssetJson(fileName)
        return Gson().fromJson<T>(json, T::class.java)
    }

    private fun loadAssetJson(fileName: String): String {
        val testContext = InstrumentationRegistry.getInstrumentation().context
        val inputStream = testContext.assets.open("json/$fileName")
        return inputStream.bufferedReader().use { it.readText() }
    }
}