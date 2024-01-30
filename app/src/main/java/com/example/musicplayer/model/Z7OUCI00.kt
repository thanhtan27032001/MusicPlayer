package com.example.musicplayer.model

import com.google.gson.annotations.SerializedName


data class Z7OUCI00 (

  @SerializedName("total_score"          ) var totalScore         : Int? = null,
  @SerializedName("total_peak_score"     ) var totalPeakScore     : Int? = null,
  @SerializedName("total_score_realtime" ) var totalScoreRealtime : Int? = null

)