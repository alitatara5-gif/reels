package com.example.videofeed.data.repository
import com.example.videofeed.data.datasource.VideoRemoteDataSource
import com.example.videofeed.data.model.VideoItem

class VideoRepository(private val remoteDataSource: VideoRemoteDataSource) {
    fun getVideos(url: String): List<VideoItem> {
        return remoteDataSource.fetchVideos(url)
    }
}
