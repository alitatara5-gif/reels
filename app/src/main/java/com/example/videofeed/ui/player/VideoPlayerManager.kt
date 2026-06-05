package com.example.videofeed.ui.player

import android.content.Context
import android.view.View
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.videofeed.util.AspectRatioUtil

class VideoPlayerManager(private val context: Context) {
    private var exoPlayer: ExoPlayer? = null
    private var currentPlayerView: PlayerView? = null

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
            currentPlayerView?.let { playerView ->
                playerView.resizeMode = AspectRatioUtil.getResizeMode(videoSize)
            }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            when (playbackState) {
                Player.STATE_READY -> {
                    // Tampilkan PlayerView saat video siap dimuat
                    currentPlayerView?.visibility = View.VISIBLE
                }
                else -> {}
            }
        }
    }

    fun playVideo(url: String, playerView: PlayerView) {
        val player = getPlayer()

        // Lepaskan player dari view sebelumnya
        currentPlayerView?.player = null

        // Pasang player ke view baru
        playerView.player = player
        currentPlayerView = playerView

        // Sembunyikan PlayerView sampai video siap dimuat
        currentPlayerView?.visibility = View.INVISIBLE

        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    fun pauseVideo() {
        exoPlayer?.pause()
    }

    fun releasePlayer() {
        exoPlayer?.removeListener(playerListener)
        exoPlayer?.release()
        exoPlayer = null
        currentPlayerView = null
    }
}
