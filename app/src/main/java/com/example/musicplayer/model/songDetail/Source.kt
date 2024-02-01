package com.example.musicplayer.model.songDetail

import com.google.gson.annotations.SerializedName


data class Source (

  @SerializedName("128" ) var quality128 : String? = null,
  @SerializedName("320" ) var quality320 : String? = null

)