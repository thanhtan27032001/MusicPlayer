package com.example.musicplayer.model.song_chart

import com.google.gson.annotations.SerializedName


data class ResponseSongsChart (

  @SerializedName("err"       ) var err       : Int?    = null,
  @SerializedName("msg"       ) var msg       : String? = null,
  @SerializedName("data"      ) var data      : Data?   = Data(),
  @SerializedName("timestamp" ) var timestamp : Int?    = null

)