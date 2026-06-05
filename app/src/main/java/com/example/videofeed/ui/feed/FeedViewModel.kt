package com.example.videofeed.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.videofeed.data.model.VideoItem
import com.example.videofeed.data.repository.VideoRepository
import kotlin.concurrent.thread

class FeedViewModel(private val repository: VideoRepository) : ViewModel() {
    private val _videos = MutableLiveData<List<VideoItem>>()
    val videos: LiveData<List<VideoItem>> get() = _videos

    fun loadVideos(url: String) {
        thread {
            val fetchedVideos = repository.getVideos(url)
            _videos.postValue(fetchedVideos)
        }
    }
}
