package com.example.videofeed.di

import com.example.videofeed.data.datasource.VideoRemoteDataSource
import com.example.videofeed.data.repository.VideoRepository

// Sederhana Manual Dependency Injection
object AppModule {
    private val videoRemoteDataSource by lazy { VideoRemoteDataSource() }
    val videoRepository by lazy { VideoRepository(videoRemoteDataSource) }
}
