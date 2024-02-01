package com.example.musicplayer.model.songDetail

import com.example.musicplayer.model.songsChart.Album
import com.example.musicplayer.model.songsChart.Artist
import com.example.musicplayer.model.songsChart.Artists
import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("id"            ) var id           : String?            = null,
  @SerializedName("name"          ) var name         : String?            = null,
  @SerializedName("title"         ) var title        : String?            = null,
  @SerializedName("code"          ) var code         : String?            = null,
  @SerializedName("content_owner" ) var contentOwner : Int?               = null,
  @SerializedName("isoffical"     ) var isoffical    : Boolean?           = null,
  @SerializedName("isWorldWide"   ) var isWorldWide  : Boolean?           = null,
  @SerializedName("playlist_id"   ) var playlistId   : String?            = null,
  @SerializedName("artists"       ) var artists      : ArrayList<Artists> = arrayListOf(),
  @SerializedName("artists_names" ) var artistsNames : String?            = null,
  @SerializedName("performer"     ) var performer    : String?            = null,
  @SerializedName("type"          ) var type         : String?            = null,
  @SerializedName("link"          ) var link         : String?            = null,
  @SerializedName("lyric"         ) var lyric        : String?            = null,
  @SerializedName("thumbnail"     ) var thumbnail    : String?            = null,
  @SerializedName("duration"      ) var duration     : Int?               = null,
  @SerializedName("source"        ) var source       : Source?            = Source(),
  @SerializedName("album"         ) var album        : Album?             = Album(),
  @SerializedName("artist"        ) var artist       : Artist?            = Artist(),
  @SerializedName("ads"           ) var ads          : Boolean?           = null,
  @SerializedName("is_vip"        ) var isVip        : Boolean?           = null,
  @SerializedName("ip"            ) var ip           : String?            = null

)