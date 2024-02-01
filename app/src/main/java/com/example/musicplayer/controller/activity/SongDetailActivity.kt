package com.example.musicplayer.controller.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicplayer.R
import com.example.musicplayer.api.SongDetailApi
import com.example.musicplayer.utils.MyRetrofit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call

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
        GlobalScope.launch {
            val response = api.getSongDetail(key = songId).execute()
            if (response.isSuccessful) {
                println("Get song detail successfully")
                println(response.body()?.msg)
            }
            else {
                println("Get song detail fail")
            }
        }
    }
}