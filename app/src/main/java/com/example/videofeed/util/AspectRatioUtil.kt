package com.example.videofeed.util

import androidx.media3.common.VideoSize
import androidx.media3.ui.AspectRatioFrameLayout

object AspectRatioUtil {
    fun getResizeMode(videoSize: VideoSize): Int {
        return if (videoSize.width > videoSize.height) {
            // Landscape: Jangan di-crop, munculkan black bar atas bawah jika perlu
            AspectRatioFrameLayout.RESIZE_MODE_FIT
        } else {
            // Portrait: Penuhi layar, potong sedikit pinggiran jika layar lebih panjang
            AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }
    }
}
