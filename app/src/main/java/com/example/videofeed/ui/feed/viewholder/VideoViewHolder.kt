package com.example.videofeed.ui.feed.viewholder

import android.view.View
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView
import com.example.videofeed.R
import com.example.videofeed.data.model.VideoItem
import com.example.videofeed.ui.player.VideoPlayerManager
import com.example.videofeed.util.AspectRatioUtil

class VideoViewHolder(
    itemView: View,
    private val playerManager: VideoPlayerManager
) : RecyclerView.ViewHolder(itemView) {

    private val playerView: PlayerView = itemView.findViewById(R.id.playerView)
    private var currentUrl: String? = null

    fun bind(videoItem: VideoItem) {
        currentUrl = videoItem.videoUrl
        // Reset thumbnail / UI details here if needed
    }

    fun playVideo() {
        currentUrl?.let { url ->
            val player = playerManager.getPlayer()
            playerView.player = player
            
            // Logika utama untuk Portrait vs Landscape
            player.addListener(object : Player.Listener {
                override fun onVideoSizeChanged(videoSize: VideoSize) {
                    playerView.resizeMode = AspectRatioUtil.getResizeMode(videoSize)
                }
            })

            playerManager.playVideo(url)
        }
    }

    fun pauseVideo() {
        playerView.player?.pause()
    }
}
