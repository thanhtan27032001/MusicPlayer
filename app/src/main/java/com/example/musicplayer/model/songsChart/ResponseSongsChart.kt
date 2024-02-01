package com.example.musicplayer.model.songsChart

import com.google.gson.annotations.SerializedName


data class ResponseSongsChart (

  @SerializedName("err"       ) var err       : Int?    = null,
  @SerializedName("msg"       ) var msg       : String? = null,
  @SerializedName("data"      ) var data      : Data?   = Data(),
  @SerializedName("timestamp" ) var timestamp : Long?    = null

)