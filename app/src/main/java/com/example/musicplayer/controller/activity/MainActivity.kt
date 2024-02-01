package com.example.musicplayer.controller.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.controller.adapter.SongsChartAdapter
import com.example.musicplayer.model.songsChart.Song
import com.example.musicplayer.service.api.GetSongsChartApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var rvSongsChart: RecyclerView
    private lateinit var songsChartAdapter: SongsChartAdapter
    private lateinit var songsChartArray: ArrayList<Song>
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
                val resSongsChart = response.body()
                println("Call api successfully")
                println(resSongsChart!!.data!!.song[0].name!!)
                // set data recycler view
                songsChartArray = resSongsChart.data!!.song

                withContext(Dispatchers.Main){
                    songsChartAdapter = SongsChartAdapter(songsChartArray, this@MainActivity)
                    rvSongsChart.adapter = songsChartAdapter
                    rvSongsChart.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                }
            }
            else {
                println("Call api fail: ${response.errorBody()}")
            }
        }
    }
}