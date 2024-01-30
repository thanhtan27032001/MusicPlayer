package com.example.musicplayer.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.controller.adapter.SongsChartAdapter
import com.example.musicplayer.model.SongFake

class MainActivity : AppCompatActivity() {
    private lateinit var rvSongsChart: RecyclerView
    private lateinit var songsChartAdapter: SongsChartAdapter
    private lateinit var songsChartArray: ArrayList<SongFake>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setView()
        setEvent()
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
    }
}