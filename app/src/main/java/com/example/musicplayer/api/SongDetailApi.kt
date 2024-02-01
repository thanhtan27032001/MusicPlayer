package com.example.musicplayer.api

import com.example.musicplayer.model.songDetail.ResponseSongDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SongDetailApi {
    @GET("/xhr/media/get-source")
    fun getSongDetail(
        @Query("type") type: String = "audio",
        @Query("key") key: String
    ): Call<ResponseSongDetail>
}