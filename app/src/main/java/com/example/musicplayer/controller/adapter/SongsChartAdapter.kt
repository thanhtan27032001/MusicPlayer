package com.example.musicplayer.controller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.R
import com.example.musicplayer.controller.activity.MainActivity
import com.example.musicplayer.model.songsChart.Song
import com.squareup.picasso.Picasso

class SongsChartAdapter(
    private val songsChart: ArrayList<Song>,
    private val context: MainActivity
) : RecyclerView.Adapter<SongsChartAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSongRank: TextView
        val imgSongThumbnail: ImageView
        val tvSongName: TextView
        val tvSongPerformers: TextView
        val btnPlay: ImageView
        val layoutItemSongsChart: LinearLayout

        init {
            tvSongRank = itemView.findViewById(R.id.tvSongRank)
            imgSongThumbnail = itemView.findViewById(R.id.imgSongThumbnail)
            tvSongName = itemView.findViewById(R.id.tvSongName)
            tvSongPerformers = itemView.findViewById(R.id.tvSongPerformers)
            btnPlay = itemView.findViewById(R.id.btnPlay)
            layoutItemSongsChart = itemView.findViewById(R.id.layoutItemSongChart)
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
        holder.tvSongRank.text = song.position.toString()
        holder.tvSongName.text = song.name
        holder.tvSongPerformers.text = song.performer
        Picasso.get().load(song.thumbnail).into(holder.imgSongThumbnail)

        holder.layoutItemSongsChart.setOnClickListener {
            Toast.makeText(context, "Play song rank #${song.position.toString()}", Toast.LENGTH_SHORT).show()
            context.playSong(song.code!!)
        }
    }
}