package com.example.videofeed.ui.feed.viewholder

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView
import com.example.videofeed.R
import com.example.videofeed.data.model.VideoItem
import com.example.videofeed.ui.player.VideoPlayerManager

class VideoViewHolder(
    itemView: View,
    private val playerManager: VideoPlayerManager
) : RecyclerView.ViewHolder(itemView) {

    private val playerView: PlayerView = itemView.findViewById(R.id.playerView)
    private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    
    private var currentUrl: String? = null

    fun bind(videoItem: VideoItem) {
        currentUrl = videoItem.videoUrl
        tvTitle.text = videoItem.title
    }

    fun playVideo() {
        currentUrl?.let { url ->
            playerManager.playVideo(url, playerView, progressBar)
        }
    }

    fun pauseVideo() {
        playerManager.pauseVideo()
    }
}
