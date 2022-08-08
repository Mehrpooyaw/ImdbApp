package com.example.imdbapp.network.model.youtube.downloader.playlist

import com.example.imdbapp.network.model.youtube.downloader.playlist.Video

data class YoutubePlaylist(
    val auhtor: String?,
    val errorMessage: String?,
    val title: String?,
    val videos: List<Video>?
)