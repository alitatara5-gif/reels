package com.example.videofeed.data.datasource

import com.example.videofeed.data.model.VideoItem

class VideoRemoteDataSource {
    // Simulasi mengambil data dari website spesifik Anda
    fun fetchVideos(): List<VideoItem> {
        return listOf(
            VideoItem("1", "https://www.w3schools.com/html/mov_bbb.mp4", "Landscape Video Test"),
            // Tambahkan link video spesifik Anda di sini
            VideoItem("2", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4", "Video Test 2")
        )
    }
}
