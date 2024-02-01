package com.example.musicplayer.controller.activity

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.musicplayer.R
import com.example.musicplayer.api.SongDetailApi
import com.example.musicplayer.model.songDetail.ResponseSongDetail
import com.example.musicplayer.utils.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongDetailActivity : AppCompatActivity() {

    private lateinit var responseSongDetail: ResponseSongDetail
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var btnPlay: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        setView()
        setEvent()
        setData()

    }

    private fun prepareMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(responseSongDetail.data?.source?.quality128)
        mediaPlayer.prepare()
    }

    companion object {
        const val SONG_CODE: String = "code"
    }

    private fun setView(){
        btnPlay = findViewById(R.id.btnPlay)
    }

    private fun setEvent(){
        btnPlay.setOnClickListener {
            playSong()
        }
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
                    responseSongDetail = response.body()!!
                    prepareMediaPlayer()
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

    private fun playSong() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
        else {
            mediaPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}