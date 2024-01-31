package com.example.musicplayer.model.song_chart

class SongFake {
    var rank: Int
    var name: String
    var performers: String
    var imageUrl: String

    constructor(rank: Int, name: String, performers: String, imageUrl: String) {
        this.rank = rank
        this.name = name
        this.performers = performers
        this.imageUrl = imageUrl
    }
}