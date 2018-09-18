package com.demo.architecturecomponentstalk.api

import android.arch.lifecycle.LiveData
import com.demo.architecturecomponentstalk.api.json.MeetupEventJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeetupApi {

//    @GET("{urlname}/events")
//    fun getEvents(@Path("urlname") urlname: String): Map<String, MeetupEventJson>

    @GET("{urlname}/events/{id}")
    fun getEventById(
            @Path("urlname") urlname: String,
            @Path("id") id: String,
            @Query("key") key:String,
            @Query("sign") sign:Boolean)
            : Call<MeetupEventJson>
}