package com.example.musicplayer.service

import com.example.musicplayer.model.song_chart.ResponseSongsChart
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetSongsChartApi {
    @GET("/xhr/chart-realtime")
    fun getSongsChart(
        @Query("songId") songId: Int = 0,
        @Query("videoId") videoId: Int = 0,
        @Query("albumId") albumId: Int = 0,
        @Query("chart") chart: String = "song",
        @Query("time") time: Int = -1
    ): Call<ResponseSongsChart>
}