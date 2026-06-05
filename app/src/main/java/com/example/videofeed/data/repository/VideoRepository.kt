package com.example.videofeed.data.repository

import com.example.videofeed.data.datasource.VideoRemoteDataSource
import com.example.videofeed.data.model.VideoItem

class VideoRepository(private val remoteDataSource: VideoRemoteDataSource) {
    fun getVideos(): List<VideoItem> {
        return remoteDataSource.fetchVideos()
    }
}
