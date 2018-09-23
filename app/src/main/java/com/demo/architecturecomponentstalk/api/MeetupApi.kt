package com.demo.architecturecomponentstalk.api

import android.location.Location
import com.demo.architecturecomponentstalk.api.json.MeetupEventJson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeetupApiInterface {

    @GET("{urlname}/events")
    fun getEventsByUrl(@Path("urlname") urlname: String,
                       @Query("key") key: String,
                       @Query("sign") sign: Boolean)
            : Call<List<MeetupEventJson>>

    @GET("{urlname}/events/{id}")
    fun getEventById(
            @Path("urlname") urlname: String,
            @Path("id") id: String,
            @Query("key") key: String,
            @Query("sign") sign: Boolean)
            : Call<MeetupEventJson>
}

object MeetupApi {
    const val apiKey = "5919532b582856507c78552031717d2d"
    const val baseUrl = "https://api.meetup.com"
    val api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MeetupApiInterface::class.java)

    fun getEventsByUrlName(urlname: String): List<MeetupEventJson>? =
            api.getEventsByUrl(urlname, apiKey, true).execute().body()

    fun getEvent(urlname: String, id: String): MeetupEventJson? {
        val call = api.getEventById(urlname, id, apiKey, true)
        return call.execute().body()
    }
}

