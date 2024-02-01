package com.example.musicplayer.model.songsChart

import com.google.gson.annotations.SerializedName


data class SongHis (

    @SerializedName("min_score"   ) var minScore   : Int?    = null,
    @SerializedName("max_score"   ) var maxScore   : Double? = null,
    @SerializedName("from"        ) var from       : Int?    = null,
    @SerializedName("interval"    ) var interval   : Int?    = null,
    @SerializedName("data"        ) var data       : Data?   = Data(),
    @SerializedName("score"       ) var score      : Score?  = Score(),
    @SerializedName("total_score" ) var totalScore : Int?    = null

)