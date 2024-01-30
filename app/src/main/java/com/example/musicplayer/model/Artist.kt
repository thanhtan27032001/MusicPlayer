package com.example.musicplayer.model

import com.google.gson.annotations.SerializedName


data class Artist (

  @SerializedName("id"        ) var id        : String? = null,
  @SerializedName("name"      ) var name      : String? = null,
  @SerializedName("link"      ) var link      : String? = null,
  @SerializedName("cover"     ) var cover     : String? = null,
  @SerializedName("thumbnail" ) var thumbnail : String? = null

)