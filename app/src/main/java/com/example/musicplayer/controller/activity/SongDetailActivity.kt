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
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongDetailActivity : AppCompatActivity() {

    private lateinit var responseSongDetail: ResponseSongDetail
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayingThread: Thread

    private var isSeekBarTouch = false


    private lateinit var btnPlay: ImageView
    private lateinit var seekBarSongPlayer: SeekBar
    private lateinit var tvSongLengthCurrent: TextView
    private lateinit var tvSongLengthMax: TextView
    private lateinit var tvSongName: TextView
    private lateinit var tvSongPerformers: TextView
    private lateinit var imgSongThumbnail: ImageView

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
        tvSongName = findViewById(R.id.tvSongName)
        tvSongPerformers = findViewById(R.id.tvSongPerformers)
        imgSongThumbnail = findViewById(R.id.imgSongThumbnail)
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
                updateSongLengthCurrent(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isSeekBarTouch = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isSeekBarTouch = false
            }

        })
    }

    private fun updateSongLengthCurrent(progress: Int) {
        val minute = (progress/1000)/60
        val second = (progress/1000)%60
        val minuteString: String = minute.toString()
        val secondString: String = if(second > 10) second.toString() else "0$second"
        tvSongLengthCurrent.text = "$minuteString:$secondString"
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
                    setSongDetailInfo()
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

    private fun setSongDetailInfo() {
        tvSongName.text = responseSongDetail.data?.name
        tvSongPerformers.text = responseSongDetail.data?.performer
        if (responseSongDetail.data?.album?.thumbnailMedium != null) {
            Picasso.get().load(responseSongDetail.data?.album?.thumbnailMedium).into(imgSongThumbnail)
        }
        else {
            Picasso.get().load(responseSongDetail.data?.album?.thumbnail).into(imgSongThumbnail)
        }
    }

    private fun prepareMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(responseSongDetail.data?.source?.quality128)
        mediaPlayer.prepare()

        startTrackingPlayerThread()

        seekBarSongPlayer.progress = 0
        seekBarSongPlayer.max = mediaPlayer.duration

        tvSongLengthMax.text = "${(mediaPlayer.duration/1000)/60}:${(mediaPlayer.duration/1000)%60}"
    }

    private fun startTrackingPlayerThread() {
        mediaPlayingThread = Thread {
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
        }
        mediaPlayingThread.start()
    }

    private fun playSong() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayingThread.interrupt()
        }
        else {
            mediaPlayer.start()
            startTrackingPlayerThread()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayingThread.interrupt()
    }
}