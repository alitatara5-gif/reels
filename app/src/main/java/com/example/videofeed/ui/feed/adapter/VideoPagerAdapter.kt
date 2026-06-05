package com.example.videofeed.ui.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videofeed.R
import com.example.videofeed.data.model.VideoItem
import com.example.videofeed.ui.feed.viewholder.VideoViewHolder
import com.example.videofeed.ui.player.VideoPlayerManager

class VideoPagerAdapter(
    private val videos: List<VideoItem>,
    private val playerManager: VideoPlayerManager
) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view, playerManager)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int = videos.size

    override fun onViewAttachedToWindow(holder: VideoViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.playVideo()
    }

    override fun onViewDetachedFromWindow(holder: VideoViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.pauseVideo()
    }
}
