package com.example.musicplayer.controller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.controller.MainActivity
import com.example.musicplayer.model.SongFake

class SongsChartAdapter : RecyclerView.Adapter<SongsChartAdapter.MyViewHolder> {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    val songsChart: Array<SongFake>
    val context: MainActivity

    constructor(songsChart: Array<SongFake>, context: MainActivity) : super() {
        this.songsChart = songsChart
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_song_chart, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return songsChart.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}