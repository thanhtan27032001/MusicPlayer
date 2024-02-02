package com.example.musicplayer.controller.activity

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import com.example.musicplayer.R
import com.example.musicplayer.api.SongDetailApi
import com.example.musicplayer.model.songDetail.ResponseSongDetail
import com.example.musicplayer.utils.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.time.Duration.Companion.seconds

class SongDetailActivity : AppCompatActivity() {

    private lateinit var responseSongDetail: ResponseSongDetail
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayingThread: Thread

    private var isSeekBarTouch = false


    private lateinit var btnPlay: ImageView
    private lateinit var seekBarSongPlayer: SeekBar
    private lateinit var tvSongLengthCurrent: TextView
    private lateinit var tvSongLengthMax: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        setView()
        setEvent()
        setData()

    }

    companion object {
        const val SONG_CODE: String = "code"
    }

    private fun setView(){
        btnPlay = findViewById(R.id.btnPlay)
        seekBarSongPlayer = findViewById(R.id.seekBarSongPlayer)
        tvSongLengthCurrent = findViewById(R.id.tvSongLengthCurrent)
        tvSongLengthMax = findViewById(R.id.tvSongLengthMax)
    }

    private fun setEvent(){
        btnPlay.setOnClickListener {
            playSong()
        }

        seekBarSongPlayer.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (isSeekBarTouch) {
                    mediaPlayer.seekTo(progress)
                }
                tvSongLengthCurrent.text = "${(progress/1000)/60}:${(progress/1000)%60}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isSeekBarTouch = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isSeekBarTouch = false
            }

        })
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

    private fun prepareMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(responseSongDetail.data?.source?.quality128)
        mediaPlayer.prepare()

        mediaPlayingThread = Thread(Runnable {
            while (mediaPlayer.isPlaying) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    seekBarSongPlayer.progress = mediaPlayer.currentPosition
                }
            }
        })

        seekBarSongPlayer.progress = 0
        seekBarSongPlayer.max = mediaPlayer.duration

//        tvSongLengthCurrent.text = "0:00"
        tvSongLengthMax.text = "${(mediaPlayer.duration/1000)/60}:${(mediaPlayer.duration/1000)%60}"
    }

    private fun playSong() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
//            mediaPlayingThread.stop()
        }
        else {
            mediaPlayer.start()
            if (!mediaPlayingThread.isAlive) {
                mediaPlayingThread.start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
//        mediaPlayingThread.stop()
    }
}