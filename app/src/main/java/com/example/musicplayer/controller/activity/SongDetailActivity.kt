package com.example.musicplayer.controller.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicplayer.R

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
        println(intent.getStringExtra(SONG_CODE))
    }
}