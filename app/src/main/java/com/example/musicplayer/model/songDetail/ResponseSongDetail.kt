package com.example.musicplayer.model.songDetail

import com.google.gson.annotations.SerializedName


data class ResponseSongDetail (

  @SerializedName("err"       ) var err       : Int?    = null,
  @SerializedName("msg"       ) var msg       : String? = null,
  @SerializedName("data"      ) var data      : Data?   = Data(),
  @SerializedName("timestamp" ) var timestamp : Long?    = null

)