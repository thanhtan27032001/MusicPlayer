package com.example.musicplayer.model

import com.google.gson.annotations.SerializedName


data class Album (

  @SerializedName("id"               ) var id              : String?            = null,
  @SerializedName("link"             ) var link            : String?            = null,
  @SerializedName("title"            ) var title           : String?            = null,
  @SerializedName("name"             ) var name            : String?            = null,
  @SerializedName("isoffical"        ) var isoffical       : Boolean?           = null,
  @SerializedName("artists_names"    ) var artistsNames    : String?            = null,
  @SerializedName("artists"          ) var artists         : ArrayList<Artists> = arrayListOf(),
  @SerializedName("thumbnail"        ) var thumbnail       : String?            = null,
  @SerializedName("thumbnail_medium" ) var thumbnailMedium : String?            = null

)