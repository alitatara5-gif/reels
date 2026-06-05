package com.example.videofeed.ui.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class VideoPlayerManager(private val context: Context) {
    private var exoPlayer: ExoPlayer? = null

    fun getPlayer(): ExoPlayer {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build().apply {
                repeatMode = ExoPlayer.REPEAT_MODE_ONE // Loop seperti TikTok
            }
        }
        return exoPlayer!!
    }

    fun playVideo(url: String) {
        val player = getPlayer()
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }
}
