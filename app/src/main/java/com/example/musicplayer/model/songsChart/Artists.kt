package com.example.musicplayer.model.songsChart

import com.google.gson.annotations.SerializedName


data class Artists (

  @SerializedName("name" ) var name : String? = null,
  @SerializedName("link" ) var link : String? = null

)