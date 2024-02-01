package com.example.musicplayer.model.songsChart

import com.google.gson.annotations.SerializedName


data class Data (

    @SerializedName("song"       ) var song      : ArrayList<Song>   = arrayListOf(),
//    @SerializedName("customied"  ) var customied : ArrayList<String> = arrayListOf(),
//    @SerializedName("peak_score" ) var peakScore : Int?              = null,
//    @SerializedName("songHis"    ) var songHis   : SongHis?          = SongHis()

)