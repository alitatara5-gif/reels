package com.example.videofeed.util
import androidx.media3.common.VideoSize
import androidx.media3.ui.AspectRatioFrameLayout

object AspectRatioUtil {
    fun getResizeMode(videoSize: VideoSize): Int {
        return if (videoSize.width > videoSize.height) {
            AspectRatioFrameLayout.RESIZE_MODE_FIT
        } else {
            AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }
    }
}
