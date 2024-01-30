package com.example.musicplayer.controller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.controller.MainActivity
import com.example.musicplayer.model.SongFake

class SongsChartAdapter(
    private val songsChart: ArrayList<SongFake>,
    private val context: MainActivity
) : RecyclerView.Adapter<SongsChartAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSongRank: TextView
        val imgSongThumbnail: ImageView
        val tvSongName: TextView
        val tvSongPerformers: TextView
        val btnPlay: ImageView

        init {
            tvSongRank = itemView.findViewById(R.id.tvSongRank)
            imgSongThumbnail = itemView.findViewById(R.id.imgSongThumbnail)
            tvSongName = itemView.findViewById(R.id.tvSongName)
            tvSongPerformers = itemView.findViewById(R.id.tvSongPerformers)
            btnPlay = itemView.findViewById(R.id.btnPlay)
        }
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
        val song = songsChart[position]
        holder.tvSongRank.text = song.rank.toString()
        holder.tvSongName.text = song.name
        holder.tvSongPerformers.text = song.performers

        holder.btnPlay.setOnClickListener {
            Toast.makeText(context, "Play song rank #${song.rank}", Toast.LENGTH_SHORT).show()
        }
    }
}