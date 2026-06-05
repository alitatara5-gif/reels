package com.example.videofeed.ui.player

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.videofeed.util.AspectRatioUtil

class VideoPlayerManager(private val context: Context) {
    private var exoPlayer: ExoPlayer? = null
    private var currentPlayerView: PlayerView? = null
    private var currentProgressBar: ProgressBar? = null

    fun getPlayer(): ExoPlayer {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build().apply {
                repeatMode = ExoPlayer.REPEAT_MODE_ONE
                addListener(playerListener)
            }
        }
        return exoPlayer!!
    }

    private val playerListener = object : Player.Listener {
        override fun onVideoSizeChanged(videoSize: VideoSize) {
            currentPlayerView?.let {
                it.resizeMode = AspectRatioUtil.getResizeMode(videoSize)
            }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            when (playbackState) {
                Player.STATE_BUFFERING -> {
                    currentProgressBar?.visibility = View.VISIBLE
                }
                Player.STATE_READY -> {
                    currentPlayerView?.visibility = View.VISIBLE
                    currentProgressBar?.visibility = View.GONE
                }
                else -> {}
            }
        }
    }

    fun playVideo(url: String, playerView: PlayerView, progressBar: ProgressBar) {
        val player = getPlayer()
        currentPlayerView?.player = null
        currentPlayerView = playerView
        currentProgressBar = progressBar
        
        playerView.player = player
        playerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    fun pauseVideo() { exoPlayer?.pause() }

    fun releasePlayer() {
        exoPlayer?.removeListener(playerListener)
        exoPlayer?.release()
        exoPlayer = null
        currentPlayerView = null
        currentProgressBar = null
    }
}
