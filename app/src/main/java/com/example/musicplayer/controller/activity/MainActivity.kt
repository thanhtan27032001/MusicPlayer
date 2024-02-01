package com.example.musicplayer.controller.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.controller.adapter.SongsChartAdapter
import com.example.musicplayer.model.songsChart.Song
import com.example.musicplayer.api.SongsChartApi
import com.example.musicplayer.model.songsChart.ResponseSongsChart
import com.example.musicplayer.utils.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val service = MyRetrofit.instance.create(SongsChartApi::class.java)
        service.getSongsChart().enqueue(object : Callback<ResponseSongsChart> {
            override fun onResponse(
                call: Call<ResponseSongsChart>,
                response: Response<ResponseSongsChart>
            ) {
                if (response.isSuccessful) {
                    val resSongsChart = response.body()
                    println("Call api successfully")
                    println(resSongsChart!!.data!!.song[0].name!!)
                    // set data recycler view
                    songsChartArray = resSongsChart.data!!.song

                    songsChartAdapter = SongsChartAdapter(songsChartArray, this@MainActivity)
                    rvSongsChart.adapter = songsChartAdapter
                    rvSongsChart.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                }
                else {
                    println("Call api fail: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ResponseSongsChart>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun playSong(songCode: String) {
        val intent = Intent(this@MainActivity, SongDetailActivity::class.java)
        intent.putExtra(SongDetailActivity.SONG_CODE, songCode)
        startActivity(intent)
    }
}