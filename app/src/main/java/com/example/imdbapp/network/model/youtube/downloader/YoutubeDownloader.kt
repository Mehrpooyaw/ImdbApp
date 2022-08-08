package com.example.imdbapp.network.model.youtube.downloader

data class YoutubeDownloader(
    val description: String?,
    val duration: String?,
    val errorMessage: String?,
    val image: String?,
    val title: String?,
    val uploadDate: String?,
    val videoId: String?,
    val videos: List<Video>?
)