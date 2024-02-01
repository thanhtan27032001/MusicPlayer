package com.example.musicplayer.model.songsChart

import com.google.gson.annotations.SerializedName


data class Song (

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
  @SerializedName("total"         ) var total        : Int?               = null,
  @SerializedName("rank_num"      ) var rankNum      : String?            = null,
  @SerializedName("rank_status"   ) var rankStatus   : String?            = null,
  @SerializedName("artist"        ) var artist       : Artist?            = Artist(),
  @SerializedName("position"      ) var position     : Int?               = null,
  @SerializedName("order"         ) var order        : String?            = null,
  @SerializedName("album"         ) var album        : Album?             = Album()

)