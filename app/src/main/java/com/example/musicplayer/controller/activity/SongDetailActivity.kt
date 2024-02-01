package com.example.musicplayer.controller.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicplayer.R
import com.example.musicplayer.api.SongDetailApi
import com.example.musicplayer.model.songDetail.ResponseSongDetail
import com.example.musicplayer.utils.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        setData()

    }

    companion object {
        const val SONG_CODE: String = "code"
    }

    private fun setData(){
        if (intent.getStringExtra(SONG_CODE) != null) {
            println(intent.getStringExtra(SONG_CODE))
            getSongDetail(intent.getStringExtra(SONG_CODE)!!)
        }
    }

    private fun getSongDetail(songId: String){
        val api = MyRetrofit.instance.create(SongDetailApi::class.java)
        api.getSongDetail(key = songId).enqueue(object : Callback<ResponseSongDetail>{
            override fun onResponse(
                call: Call<ResponseSongDetail>,
                response: Response<ResponseSongDetail>
            ) {
                if (response.isSuccessful) {
                    println("Get song detail successfully")
                    println(response.body()?.msg)
                }
                else {
                    println("Get song detail fail")
                }
            }

            override fun onFailure(call: Call<ResponseSongDetail>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}