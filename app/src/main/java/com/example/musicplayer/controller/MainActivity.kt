package com.example.musicplayer.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.controller.adapter.SongsChartAdapter
import com.example.musicplayer.model.song_chart.ResponseSongsChart
import com.example.musicplayer.model.song_chart.SongFake
import com.example.musicplayer.service.GetSongsChartApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var rvSongsChart: RecyclerView
    private lateinit var songsChartAdapter: SongsChartAdapter
    private lateinit var songsChartArray: ArrayList<SongFake>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ui
        setView()
        setEvent()

        // data
        setData()
    }

    private fun setView() {
        rvSongsChart = findViewById(R.id.rvSongsChart)
    }

    private fun setEvent() {

    }

    private fun setData() {
        songsChartArray = arrayListOf(
            SongFake(1, "Bài hát top 1", "Nguyễn Văn A", ""),
            SongFake(2, "Bài hát top 1", "Nguyễn Văn A", ""),
            SongFake(3, "Bài hát top 1", "Nguyễn Văn A", ""),
            SongFake(4, "Bài hát top 1", "Nguyễn Văn A", ""),
            SongFake(5, "Bài hát top 1", "Nguyễn Văn A", ""),
            SongFake(6, "Bài hát top 1", "Nguyễn Văn A", ""),
            SongFake(7, "Bài hát top 1", "Nguyễn Văn A", ""),
            SongFake(8, "Bài hát top 1", "Nguyễn Văn A", ""),
            SongFake(9, "Bài hát top 1", "Nguyễn Văn A", ""),
            SongFake(10, "Bài hát top 1", "Nguyễn Văn A", ""),
        )

        songsChartAdapter = SongsChartAdapter(songsChartArray, this)
        rvSongsChart.adapter = songsChartAdapter
        rvSongsChart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        fetchSongsChart()
    }

    private fun fetchSongsChart() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mp3.zing.vn/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GetSongsChartApi::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val response = service.getSongsChart().execute()
            if (response.isSuccessful) {
                println("Call api successfully")
            }
            else {
                println("Call api fail: ${response.errorBody()}")
            }
        }
    }
}